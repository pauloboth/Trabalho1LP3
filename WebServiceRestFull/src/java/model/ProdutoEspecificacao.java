package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "produtoespecificacao")
@XmlRootElement
public class ProdutoEspecificacao implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "pro_id", referencedColumnName = "pro_id")
    private Produto produto;

    @Id
    @ManyToOne
    @JoinColumn(name = "esp_id", referencedColumnName = "esp_id")
    private Especificacao especificacao;

    private String prs_valor;

    public ProdutoEspecificacao() {
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Especificacao getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(Especificacao especificacao) {
        this.especificacao = especificacao;
    }

    public String getPrs_valor() {
        return prs_valor;
    }

    public void setPrs_valor(String prs_valor) {
        this.prs_valor = prs_valor;
    }

}
