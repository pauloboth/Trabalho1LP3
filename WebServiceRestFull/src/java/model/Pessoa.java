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
@Table(name = "pessoa")
@XmlRootElement
public class Pessoa implements Serializable {

    @Id
    @SequenceGenerator(name = "pes_id", sequenceName = "seq_pes_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "pes_id")
    private int pes_id;
    private String pes_nome;
    private String pes_email;
    private String pes_senha;
    private String pes_login;
    private Date pes_data;
    private String pes_cidade;
    private String pes_estado;
    private String pes_pais;

    @ManyToOne
    @JoinColumn(name = "gro_id", referencedColumnName = "gro_id")
    private Grupo grupo;

    public Pessoa() {
    }

    public int getPes_id() {
        return pes_id;
    }

    public void setPes_id(int pes_id) {
        this.pes_id = pes_id;
    }

    public String getPes_nome() {
        return pes_nome;
    }

    public void setPes_nome(String pes_nome) {
        this.pes_nome = pes_nome;
    }

    public String getPes_email() {
        return pes_email;
    }

    public void setPes_email(String pes_email) {
        this.pes_email = pes_email;
    }

    public String getPes_senha() {
        return pes_senha;
    }

    public void setPes_senha(String pes_senha) {
        this.pes_senha = pes_senha;
    }

    public String getPes_login() {
        return pes_login;
    }

    public void setPes_login(String pes_login) {
        this.pes_login = pes_login;
    }

    public Date getPes_data() {
        return pes_data;
    }

    public void setPes_data(Date pes_data) {
        this.pes_data = pes_data;
    }

    public String getPes_cidade() {
        return pes_cidade;
    }

    public void setPes_cidade(String pes_cidade) {
        this.pes_cidade = pes_cidade;
    }

    public String getPes_estado() {
        return pes_estado;
    }

    public void setPes_estado(String pes_estado) {
        this.pes_estado = pes_estado;
    }

    public String getPes_pais() {
        return pes_pais;
    }

    public void setPes_pais(String pes_pais) {
        this.pes_pais = pes_pais;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.pes_id;
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
        final Pessoa other = (Pessoa) obj;
        if (this.pes_id != other.pes_id) {
            return false;
        }
        return true;
    }

    public String toString() {
        return getPes_nome();
    }
}
