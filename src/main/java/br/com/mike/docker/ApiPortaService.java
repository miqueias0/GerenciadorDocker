package br.com.mike.docker;

import br.com.mike.docker.modelo.ApiPorta;
import br.com.mike.docker.repository.ApiPortaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ApiPortaService {

    @Inject
    private ApiPortaRepository repository;

    @ActivateRequestContext
    public ApiPorta obterApiPortaById(Long id){
        return repository.findById(id).orElse(null);
    }

    @ActivateRequestContext
    public List<ApiPorta> obterListaApiPorta(String name){
        return repository.findAllByEndpoint(name).orElse(null);
    }

    @Transactional
    public ApiPorta save(ApiPorta apiPorta){
        return repository.save(apiPorta);
    }

    @Transactional
    public void delete(ApiPorta apiPorta){
        repository.delete(apiPorta);
    }
}
