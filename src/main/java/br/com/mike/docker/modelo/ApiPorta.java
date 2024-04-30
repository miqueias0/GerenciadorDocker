package br.com.mike.docker.modelo;

 import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "porta")
public class ApiPorta implements Serializable {

    @Id
    @Column(name = "porta", unique = true, nullable = false)
    private Long porta;
    @Column(name = "endpoint", length = 100)
    private String endpoint;
    @Column(name = "quantidade_requisicao")
    private Long quantidadeRequisicao;
    @JsonbDateFormat(JsonbDateFormat.TIME_IN_MILLIS)
    @Column(name="data_ultima_requisicao", columnDefinition = "timestamp")
    private Date dataUltimaRequisicao;

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

    public Date getDataUltimaRequisicao() {
        return dataUltimaRequisicao;
    }

    public void setDataUltimaRequisicao(Date dataUltimaRequisicao) {
        this.dataUltimaRequisicao = dataUltimaRequisicao;
    }
}
