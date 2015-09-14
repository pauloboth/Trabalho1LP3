package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProdutoEspecificacao implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "pro_id", referencedColumnName = "pro_id")
    private Produto produto;

    @Id
    @ManyToOne
    @JoinColumn(name = "esp_id", referencedColumnName = "esp_id")
    private Especificacao especificacao;

    private String prs_descricao;

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

    public String getPrs_descricao() {
        return prs_descricao;
    }

    public void setPrs_descricao(String prs_descricao) {
        this.prs_descricao = prs_descricao;
    }
    
    
}
