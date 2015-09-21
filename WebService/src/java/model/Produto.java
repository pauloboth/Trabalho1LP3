package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
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

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "pro_id", referencedColumnName = "pro_id")
    private List<ProdutoEspecificacao> produto_esp;

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

    public void addProdutoEsp() {
        if (this.produto_esp == null) {
            this.produto_esp = new ArrayList<>();
        }
        ProdutoEspecificacao e = new ProdutoEspecificacao();
        this.produto_esp.add(e);
    }

    public List<ProdutoEspecificacao> getProdutoEsp() {
        return produto_esp;
    }

    public void setProdutoEsp(List<ProdutoEspecificacao> proesp) {
        this.produto_esp = proesp;
    }

    public void deProdutoEsp(ProdutoEspecificacao p) {
        if (this.produto_esp != null) {
            this.produto_esp.remove(p);
        }
    }
}
