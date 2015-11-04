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
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "produto")
@XmlRootElement
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
    private Date pro_data;
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

    public Date getPro_data() {
        return pro_data;
    }

    public void setPro_data(Date pro_data) {
        this.pro_data = pro_data;
    }

    public double getPro_preco() {
        return pro_preco;
    }

    public void setPro_preco(double pro_preco) {
        this.pro_preco = pro_preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<ProdutoEspecificacao> getLsProdutoEspecificacao() {
        return lsProdutoEspecificacao;
    }

    public void setLsProdutoEspecificacao(List<ProdutoEspecificacao> lsProdutoEspecificacao) {
        this.lsProdutoEspecificacao = lsProdutoEspecificacao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.pro_id;
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

    public String toStatus() {
        if (pro_status == 1) {
            return "Ativo";
        } else if (pro_status == 2) {
            return "Bloqueado";
        } else if (pro_status == 3) {
            return "Deletado";
        } else {
            return "Nenhum";
        }
    }

    public String toTipo() {
        if (pro_tipo == 1) {
            return "Produto";
        } else if (pro_tipo == 2) {
            return "Serviço";
        } else {
            return "Nenhum";
        }
    }
}
