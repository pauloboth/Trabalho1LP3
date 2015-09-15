package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Especificacao implements Serializable {

    @Id
    @SequenceGenerator(name = "esp_id", sequenceName = "seq_esp_idF")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "esp_id")
    private int esp_id;
    private String esp_nome;
    private int status;

    @Id
    @ManyToOne
    @JoinColumn(name = "pro_id", referencedColumnName = "pro_id")
    private Produto produto;

    public Especificacao() {
    }

    public int getEsp_id() {
        return esp_id;
    }

    public void setEsp_id(int esp_id) {
        this.esp_id = esp_id;
    }

    public String getEsp_nome() {
        return esp_nome;
    }

    public void setEsp_nome(String esp_nome) {
        this.esp_nome = esp_nome;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
