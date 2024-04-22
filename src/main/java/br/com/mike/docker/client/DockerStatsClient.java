package br.com.mike.docker.client;

import br.com.mike.docker.modelo.DockerContainerStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class DockerStatsClient {

    private RestTemplate restTemplate;

    @Autowired
    public DockerStatsClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public DockerContainerStats obterDockerStats(String id){
        return restTemplate.getForObject("http://localhost:2375/containers/" + id + "/stats?stream=0", DockerContainerStats.class);
    }
}
