package br.com.mike.docker;

public class DockerVerificador extends Thread{
    private DockerService dockerClient;

    public DockerVerificador(DockerService dockerClient) throws Exception {
        this.dockerClient = dockerClient;
    }

    @Override
    public void run() {
        try {
            dockerClient.verificarStatsDocker(dockerClient.dockerClient);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
