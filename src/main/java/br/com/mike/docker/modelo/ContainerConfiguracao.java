package br.com.mike.docker.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "container_configuracao")
public class ContainerConfiguracao {

    @Id
    @Column(name="nome", unique = true, length = 100)
    private String nome;
    @Column(name="nano_cpu")
    private Double nanoCpu;
    @Column(name="memoria")
    private Double memoria;
    @Column(name="porta_publica")
    private Boolean portaPublica;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getNanoCpu() {
        return nanoCpu;
    }

    public void setNanoCpu(Double nanoCpu) {
        this.nanoCpu = nanoCpu;
    }

    public Double getMemoria() {
        return memoria;
    }

    public void setMemoria(Double memoria) {
        this.memoria = memoria;
    }

    public Boolean getPortaPublica() {
        return portaPublica;
    }

    public void setPortaPublica(Boolean portaPublica) {
        this.portaPublica = portaPublica;
    }
}
