package br.com.mike.docker;

import br.com.mike.docker.modelo.DockerImagemTag;
import br.com.mike.docker.repository.DockerImagemTagRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DockerImagemTagService {

    @Inject
    private DockerImagemTagRepository repository;

    public DockerImagemTag findById(String id){
        return repository.findById(id).orElse(null);
    }

    public DockerImagemTag findByNome(String nome){
        return repository.findByNome(nome);
    }
}
