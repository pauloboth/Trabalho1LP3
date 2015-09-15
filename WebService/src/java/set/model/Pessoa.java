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
    private int pes_id;

    private String pes_nome;

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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.pes_id;
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

    @Override
    public String toString() {
        return getPes_nome();
    }

}
