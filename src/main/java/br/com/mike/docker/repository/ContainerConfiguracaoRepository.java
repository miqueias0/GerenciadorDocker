package br.com.mike.docker.repository;

import br.com.mike.docker.modelo.ContainerConfiguracao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContainerConfiguracaoRepository extends JpaRepository<ContainerConfiguracao, String> {
    ContainerConfiguracao findByNome(String nome);
}
