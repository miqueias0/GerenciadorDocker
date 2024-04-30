package br.com.mike;

import br.com.mike.docker.ApiPortaService;
import br.com.mike.docker.DockerImagemTagService;
import br.com.mike.docker.DockerService;
import br.com.mike.docker.DockerVerificador;
import br.com.mike.docker.modelo.ApiPorta;
import br.com.mike.docker.modelo.DockerImagemTag;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.runtime.Startup;
import io.quarkus.virtual.threads.VirtualThreads;
import io.quarkus.virtual.threads.VirtualThreadsRecorder;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.checkerframework.checker.units.qual.Time;

import java.util.Date;


@Path("/api/docker")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Startup
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @Inject
    private DockerService dockerService;
    @Inject
    private ApiPortaService portaService;
    @Inject
    private DockerImagemTagService dockerImagemTagService;

    @POST
    @Path("/subir")
    public String subirContainer(@QueryParam("container") String container, @QueryParam("imagem") String imagem) throws Exception {
        dockerService.subirContainer(imagem, container);
        return "Container levantado com sucesso!";
    }

    @GET
    @Path("/subirComNome")
    public Response subirComNome(@QueryParam("container") String container) throws Exception {
        DockerImagemTag dockerImagemTag = dockerImagemTagService.findByNome(container);
        while (dockerService.verificarContainerExistente(dockerImagemTag.getNome())){
            dockerImagemTag.setNome(dockerService.adicionarNumeroContainer(dockerImagemTag.getNome()));
        }
        Integer ultimaPorta = dockerService.ultimaPorta() + 1;
        dockerService.subirContainer(dockerImagemTag.getImagemTag(), dockerImagemTag.getNome(), ultimaPorta);
        Thread.sleep(1000*10);
        ApiPorta apiPorta = dockerService.montarApiPorta(Long.valueOf(ultimaPorta), dockerImagemTag.getNome());
        apiPorta.setDataUltimaRequisicao(new Date());
        return ResponseMapper.okJson(apiPorta).build();
    }

    @GET
    @Path("/derrubar")
    @Consumes(MediaType.TEXT_PLAIN)
    public String derrubarContainer(@QueryParam("container") String container) {
        dockerService.derrubarContainer(container);
        return "Container derrubado com sucesso!";
    }

    @POST
    @Path("/pausar")
    public String pausarContainer(@QueryParam("container") String container) {
        dockerService.pausarContainer(container);
        return "Container derrubado com sucesso!";
    }

    @POST
    @Path("/despausar")
    @Time
    public String despausarContainer(@QueryParam("container") String container) {
        dockerService.despausarContainer(container);
        return "Container derrubado com sucesso!";
    }

    @POST
    @Path("/obterApiPortaById")
    public Response obterApiPortaById(@QueryParam("id") Long porta) {
        return ResponseMapper.okJson(portaService.obterApiPortaById(porta)).build();
    }

    @POST
    @Path("/obterListaApiPorta")
    public Response obterListaApiPorta(@QueryParam("name") String name) {
        return ResponseMapper.okJson(portaService.obterListaApiPorta(name)).build();
    }

    @GET
    @Path("/obterLista")
    public Response obterLista() throws JsonProcessingException {
        return ResponseMapper.okJson(portaService.obterLista()).build();
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(ApiPorta apiPorta) {
        return ResponseMapper.okJson(portaService.save(apiPorta)).build();
    }

    @POST
    @Path("/obterDockerImageId")
    public Response obterDockerImageId(@QueryParam("id") String id) {
        return ResponseMapper.okJson(dockerImagemTagService.findById(id)).build();
    }

    @POST
    @Path("/obterDockerImageNome")
    public Response obterDockerImageNome(@QueryParam("name") String name) {
        return ResponseMapper.okJson(dockerImagemTagService.findByNome(name)).build();
    }

    Thread thread;
    Thread threadVerificarExistencia;

    @PostConstruct
    public void iniciarProcessoVerificacao() throws Exception {
        try{
            thread = new DockerVerificador(dockerService);
            thread.start();
        }catch (Exception ex){

        }
        try{
            threadVerificarExistencia = new Thread((Runnable) () -> {
                while (true){
                    dockerService.verificarExcluirPortasInexistentes();
                }
            });
            threadVerificarExistencia.start();
        }catch (Exception ex){

        }
    }
    @PreDestroy
    public void onDestroy() {
        try{
            thread.interrupt();
        }catch (Exception ex){
            System.out.println(ex.getLocalizedMessage());
        }
        try{
            threadVerificarExistencia.interrupt();
        }catch (Exception ex){
            System.out.println(ex.getLocalizedMessage());
        }
    }
}
