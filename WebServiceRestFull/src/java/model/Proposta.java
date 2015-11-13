package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "proposta")
@XmlRootElement
public class Proposta implements Serializable {

    @Id
    @SequenceGenerator(name = "prs_id", sequenceName = "seq_prs_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "prs_id")
    private int prs_id;
    private String prs_descricao;
    private double prs_valor;
    private Date prs_data;

    @ManyToOne
    @JoinColumn(name = "pro_id", referencedColumnName = "pro_id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "ven_id", referencedColumnName = "pes_id")
    private Pessoa vendedor;

    @ManyToOne
    @JoinColumn(name = "com_id", referencedColumnName = "pes_id")
    private Pessoa comprador;

    public Proposta() {
    }

    public int getPrs_id() {
        return prs_id;
    }

    public void setPrs_id(int prs_id) {
        this.prs_id = prs_id;
    }

    public String getPrs_descricao() {
        return prs_descricao;
    }

    public void setPrs_descricao(String prs_descricao) {
        this.prs_descricao = prs_descricao;
    }

    public double getPrs_valor() {
        return prs_valor;
    }

    public void setPrs_valor(double prs_valor) {
        this.prs_valor = prs_valor;
    }

    public Date getPrs_data() {
        return prs_data;
    }

    public void setPrs_data(Date prs_data) {
        this.prs_data = prs_data;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pessoa getVendedor() {
        return vendedor;
    }

    public void setVendedor(Pessoa vendedor) {
        this.vendedor = vendedor;
    }

    public Pessoa getComprador() {
        return comprador;
    }

    public void setComprador(Pessoa comprador) {
        this.comprador = comprador;
    }

}
