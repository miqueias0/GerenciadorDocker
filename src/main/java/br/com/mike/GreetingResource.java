package br.com.mike;

import br.com.mike.docker.DockerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.checkerframework.checker.units.qual.Time;

@Path("/docker")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @Inject
    private DockerService dockerService;

    @POST
    @Path("/subir")
    public String subirContainer(@QueryParam("container") String container, @QueryParam("imagem") String imagem) throws Exception {
        dockerService.subirContainer(imagem, container);
        return "Container levantado com sucesso!";
    }

    @POST
    @Path("/derrubar")
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

}