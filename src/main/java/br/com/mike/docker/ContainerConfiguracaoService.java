package br.com.mike.docker;

import br.com.mike.docker.modelo.ContainerConfiguracao;
import br.com.mike.docker.repository.ContainerConfiguracaoRepository;
import com.github.dockerjava.api.model.HostConfig;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;

@ApplicationScoped
public class ContainerConfiguracaoService {

    @Inject
    private ContainerConfiguracaoRepository repository;

    @ActivateRequestContext
    public ContainerConfiguracao obterConfiguracaoContainer(String nome){
        return repository.findById(nome).orElse(null);
    }

    public HostConfig criarHost(ContainerConfiguracao configuracao){
        HostConfig hostConfig = HostConfig.newHostConfig();
        if(configuracao != null){
            if(configuracao.getNanoCpu() != null){
                hostConfig.withNanoCPUs((long) (configuracao.getNanoCpu() * 1000000000));
            }
            if(configuracao.getMemoria() != null){
                hostConfig.withMemory((long) (1048576*configuracao.getMemoria()));
            }
            if(configuracao.getPortaPublica() != null){
                hostConfig.withPublishAllPorts(configuracao.getPortaPublica());
            }
        }
        return hostConfig;
    }
}
