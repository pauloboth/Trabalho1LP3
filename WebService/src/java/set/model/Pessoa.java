package set.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

    @Id
    @SequenceGenerator(name = "pes_id", sequenceName = "seq_pes_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "pes_id")
    private int pes_codigo;

    private String pes_nome;

    public Pessoa() {
    }

    public int getPes_codigo() {
        return pes_codigo;
    }

    public void setPes_codigo(int pes_codigo) {
        this.pes_codigo = pes_codigo;
    }

    public String getPes_nome() {
        return pes_nome;
    }

    public void setPes_nome(String pes_nome) {
        this.pes_nome = pes_nome;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.pes_codigo;
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
        if (this.pes_codigo != other.pes_codigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getPes_nome();
    }

}
