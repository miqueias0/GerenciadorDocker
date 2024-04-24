package br.com.mike.docker.client;

import br.com.mike.docker.modelo.DockerContainerStats;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/containers")
public interface DockerStatsClient {

    @GET
    @Path("/{id}/stats")
    @Produces(MediaType.APPLICATION_JSON)
    DockerContainerStats obterDockerStats(@PathParam("id") String id, @QueryParam("stream") int stream);
}
