package br.com.mike.docker;

import br.com.mike.docker.client.DockerStatsClient;
import br.com.mike.docker.modelo.ApiPorta;
import br.com.mike.docker.modelo.DockerContainerStats;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.PullImageResultCallback;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
//@Singleton
public class DockerService {

    public final DockerClient dockerClient;
    @Inject
    private ContainerConfiguracaoService service;
    private DockerStatsClient dockerStatsClient;

    @Inject
    private ApiPortaService apiPortaService;

    public DockerService() throws Exception {
        dockerStatsClient = RestClientBuilder.newBuilder().baseUrl(new URL("http://localhost:2375")).build(DockerStatsClient.class);
        Properties properties = new Properties();
        properties.setProperty("DOCKER_HOST", "tcp://localhost:2375");

        DefaultDockerClientConfig config
                = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withProperties(properties).build();
        ExecutorService service = Executors.newSingleThreadExecutor();
        dockerClient = service.submit(() -> DockerClientBuilder.getInstance(config).build()).get(1, TimeUnit.MINUTES);
        if (dockerClient.listNetworksCmd().exec().stream().anyMatch(net -> net.getName().contains("microservice"))) {
            return;
        }
        dockerClient.createNetworkCmd()
                .withName("microservice")
                .exec();
    }

    public void adicionarContainerRede(String containerId, String redeNome) {
        dockerClient.connectToNetworkCmd()
                .withContainerId(containerId)
                .withNetworkId(redeNome)
                .exec();
    }

    public void subirContainer(String image, String containerName) throws Exception {
        subirContainer(image, containerName, 8081);
    }

    public void subirContainer(String image, String containerName, Integer porta) throws Exception {
        if (!verificarStateContainer(containerName, "running")) {
            dockerClient.pullImageCmd(image).exec(new PullImageResultCallback()).awaitStarted();
            if (!verificarContainerExistente(containerName)) {
                List<PortBinding> portBindings = new ArrayList<>();
                portBindings.add(PortBinding.parse("0.0.0.0:" + porta + ":" + 8081));
                List<ExposedPort> list = new ArrayList<>();
                list.add(ExposedPort.parse(8081 + "/tcp"));
                HostConfig hostConfig = service.criarHost(service.obterConfiguracaoContainer(containerName.contains("_") ? containerName.split("_")[0] : containerName));
                hostConfig.withPortBindings(portBindings);
                apiPortaService.save(montarApiPorta(porta.longValue(), containerName));
                CreateContainerResponse containerResponse = dockerClient.createContainerCmd(image)
                        .withName(containerName)
                        .withHostConfig(hostConfig)
                        .withExposedPorts(list)
                        .withPortBindings(portBindings)
                        .exec();
                containerName = containerResponse.getId();
            }
            adicionarContainerRede(containerName, "microservice");
            dockerClient.startContainerCmd(containerName).exec();
        }
    }

    private ApiPorta montarApiPorta(Long porta, String containerNome){
        ApiPorta apiPorta = new ApiPorta();
        apiPorta.setPorta(porta);
        apiPorta.setEndpoint(containerNome.contains("_") ? containerNome.split("_")[0] : containerNome);
        apiPorta.setQuantidadeRequisicao(0L);
        return apiPorta;
    }

    public void derrubarContainer(String containerName) {
        try {
            dockerClient.stopContainerCmd(containerName).exec();
            dockerClient.removeContainerCmd(containerName).exec();
        } catch (Exception ex) {
            excluirContainer(containerName);
        }
    }

    public void pausarContainer(String containerName) {
        if (verificarContainerExistente(containerName)
                && verificarStateContainer(containerName, "running")) {
            dockerClient.pauseContainerCmd(containerName).exec();
        }
    }

    public void despausarContainer(String containerName) {
        if (verificarContainerExistente(containerName)
                && verificarStateContainer(containerName, "paused")) {
            dockerClient.unpauseContainerCmd(containerName).exec();
        }
    }

    private void excluirContainer(String containerName) {
        dockerClient.removeContainerCmd(containerName).exec();
    }

    private boolean verificarContainerExistente(String containerName) {
        try {
            List<Container> containers = dockerClient.listContainersCmd()
                    .withShowAll(true)
                    .exec();
            return percorrerListaContainer(containers, 0, containerName);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return false;
    }

    private boolean verificarStateContainer(String containerName, String state) {
        return percorrerListaContainerJaAtivo(dockerClient.listContainersCmd()
                .withShowAll(true)
                .exec(), 0, containerName, state);
    }

    private boolean percorrerListaContainer(List<Container> conainers, Integer index, String containerName) {
        return (percorrerListaNomes(conainers.get(index).getNames(), 0, containerName)
                || (conainers.size() < ++index && percorrerListaContainer(conainers, index, containerName)));
    }

    private boolean percorrerListaNomes(String[] conainers, Integer index, String containerName) {
        return conainers[index].substring(1).equals(containerName)
                || (conainers.length > ++index && percorrerListaNomes(conainers, index, containerName));
    }

    private boolean percorrerListaContainerJaAtivo(List<Container> conainers, Integer index, String containerName, String state) {
        return ((percorrerListaNomes(conainers.get(index).getNames(), 0, containerName)
                && conainers.get(index).getState().equalsIgnoreCase(state))
                || (conainers.size() < ++index && percorrerListaContainer(conainers, index, containerName)));
    }

    private Integer ultimaPorta(List<Container> containers) {
        Integer maior = 0;
        for (Container container : containers) {
            for (int i = 0; i < container.getPorts().length; i++) {
                maior = maior < Optional.ofNullable(container.getPorts()[i].getPublicPort()).orElse(0) ? container.getPorts()[i].getPublicPort() : maior;
            }
        }
        return maior;
    }

    public void verificarStatsDocker(DockerClient dockerClientThread) throws Exception {
        List<Container> containers = dockerClientThread.listContainersCmd()
                .withShowAll(false)
                .withStatusFilter(Arrays.asList("running"))
                .exec();
        for (Container container : containers.stream().filter(x ->
                        !(x.getImage().contains("redis")
                                || x.getImage().contains("ryuk")))
                .toList()) {
            List<String> verificados = new ArrayList<>();
            for (String nome : container.getNames()) {
                if (verificados.contains(nome.split("_")[0])) {
                    continue;
                }
                String verificar = "";
                var valor = Arrays.stream(container.getNames()).map(x -> {
                    try {
                        return nome.contains("_") ? Integer.parseInt(nome.split("_")[1]) : 0;
                    } catch (Exception ex) {
                        return 0;
                    }
                }).sorted((a, b) -> {
                    if (a > b) {
                        return 1;
                    } else if (a < b) {
                        return -1;
                    } else {
                        return 0;
                    }
                }).findFirst().orElse(0);

                if (valor > 0) {
                    verificados.add(nome.split("_")[0]);
                    verificar = nome.substring(1).split("_")[0] + "_" + valor;
                } else {
                    verificar = nome.substring(1).split("_")[0];
                }
                if (verificarCpuMemoria(verificar)) {
                    subirContainer(container.getImage(),
                            (verificar.contains("_") ? verificar.split("_")[0] + "_" +
                                    (Integer.parseInt(verificar.split("_")[1]) + 1) : verificar + "_1"), ultimaPorta(containers) + 1);
                }
            }
        }
        Thread.sleep(10000);
        verificarStatsDocker(dockerClientThread);
    }

    private boolean verificarCpuMemoria(String id) {
        DockerContainerStats dockerContainerStats = dockerStatsClient.obterDockerStats(id, 0);
        if (dockerContainerStats != null) {
            BigDecimal cpuUsadoDiferenca = BigDecimal.valueOf(dockerContainerStats.getCpu_stats().getCpu_usage().getTotal_usage())
                    .subtract(BigDecimal.valueOf(dockerContainerStats.getPrecpu_stats().getCpu_usage().getTotal_usage()));
            BigDecimal cpuSystema = BigDecimal.valueOf(dockerContainerStats.getCpu_stats().getSystem_cpu_usage())
                    .subtract(BigDecimal.valueOf(dockerContainerStats.getPrecpu_stats().getSystem_cpu_usage()));
            BigDecimal cpuPercent = (cpuUsadoDiferenca.divide(cpuSystema, 10, RoundingMode.HALF_UP))
                    .multiply(BigDecimal.valueOf(dockerContainerStats.getCpu_stats().getOnline_cpus()))
                    .multiply(BigDecimal.valueOf(100L));
            long memoriaUsada = (dockerContainerStats.getMemory_stats().getMax_usage() / dockerContainerStats.getMemory_stats().getLimit()) * 100;
            System.out.println(cpuPercent);
            return (cpuPercent.compareTo(BigDecimal.valueOf(80L)) >= 1 && cpuPercent.compareTo(BigDecimal.valueOf(100L)) <= 1) || memoriaUsada >= 80L;
        }
        return false;

    }

}
