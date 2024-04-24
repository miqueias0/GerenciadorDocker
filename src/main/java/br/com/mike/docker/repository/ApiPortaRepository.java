package br.com.mike.docker.repository;

import br.com.mike.docker.modelo.ApiPorta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApiPortaRepository extends JpaRepository<ApiPorta, Long> {
    Optional<List<ApiPorta>> findAllByEndpoint(String endpoint);
}
