package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

    @Id
    @SequenceGenerator(name = "pro_id", sequenceName = "seq_pro_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "pro_id")
    private int pro_id;
    private String pro_nome;
    private String pro_descricao;
    private int pro_tipo;
    private int pro_status;
    private int pro_estoque;
    private Date pro_cadastro;
    private double pro_preco;

    @ManyToOne
    @JoinColumn(name = "cat_id", referencedColumnName = "cat_id")
    private Categoria categoria;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProdutoEspecificacao> lsProdutoEspecificacao;

    public Produto() {
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public String getPro_nome() {
        return pro_nome;
    }

    public void setPro_nome(String pro_nome) {
        this.pro_nome = pro_nome;
    }

    public String getPro_descricao() {
        return pro_descricao;
    }

    public void setPro_descricao(String pro_descricao) {
        this.pro_descricao = pro_descricao;
    }

    public int getPro_tipo() {
        return pro_tipo;
    }

    public void setPro_tipo(int pro_tipo) {
        this.pro_tipo = pro_tipo;
    }

    public int getPro_status() {
        return pro_status;
    }

    public void setPro_status(int pro_status) {
        this.pro_status = pro_status;
    }

    public int getPro_estoque() {
        return pro_estoque;
    }

    public void setPro_estoque(int pro_estoque) {
        this.pro_estoque = pro_estoque;
    }

    public Date getPro_cadastro() {
        return pro_cadastro;
    }

    public void setPro_cadastro(Date pro_cadastro) {
        this.pro_cadastro = pro_cadastro;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getPro_preco() {
        return pro_preco;
    }

    public void setPro_preco(double pro_preco) {
        this.pro_preco = pro_preco;
    }

    public List<ProdutoEspecificacao> getLsProdutoEspecificacao() {
        return lsProdutoEspecificacao;
    }

    public void setLsProdutoEspecificacao(List<ProdutoEspecificacao> lsProdutoEspecificacao) {
        this.lsProdutoEspecificacao = lsProdutoEspecificacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.pro_id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (this.pro_id != other.pro_id) {
            return false;
        }
        return true;
    }

    public String toString() {
        return getPro_nome();
    }

}
