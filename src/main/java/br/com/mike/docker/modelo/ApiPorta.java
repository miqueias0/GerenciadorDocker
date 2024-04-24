package br.com.mike.docker.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "porta")
public class ApiPorta {

    @Id
    @Column(name = "porta", unique = true, nullable = false)
    private Long porta;
    @Column(name = "endpoint", length = 100)
    private String endpoint;
    @Column(name = "quantidade_requisicao")
    private Long quantidadeRequisicao;

    public Long getPorta() {
        return porta;
    }

    public void setPorta(Long porta) {
        this.porta = porta;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Long getQuantidadeRequisicao() {
        return quantidadeRequisicao;
    }

    public void setQuantidadeRequisicao(Long quantidadeRequisicao) {
        this.quantidadeRequisicao = quantidadeRequisicao;
    }
}
