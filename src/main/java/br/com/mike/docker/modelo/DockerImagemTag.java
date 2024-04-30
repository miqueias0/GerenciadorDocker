package br.com.mike.docker.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "docker_imagem_tag")
public class DockerImagemTag {

    @Id
    @Column(name = "imagem_tag", unique = true, nullable = false, length = 100)
    private String imagemTag;
    @Column(name = "nome", unique = true, nullable = false,length = 100)
    private String nome;

    public String getImagemTag() {
        return imagemTag;
    }

    public void setImagemTag(String imagemTag) {
        this.imagemTag = imagemTag;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
