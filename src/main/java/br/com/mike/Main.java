package br.com.mike;

import br.com.mike.docker.DockerService;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Main {

    public static void main(String... args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                DockerService dockerClient = new DockerService();
                try {
                    dockerClient.verificarStatsDocker(dockerClient.dockerClient);
                } catch (Exception e) {
                }
            }
        });
        thread.start();
        Quarkus.run(args);
    }
}
