package br.com.mike.docker.repository;

import br.com.mike.docker.modelo.DockerImagemTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DockerImagemTagRepository extends JpaRepository<DockerImagemTag, String> {

    DockerImagemTag findByNome(String nome);
}
