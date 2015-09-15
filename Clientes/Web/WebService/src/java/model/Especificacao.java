package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Especificacao implements Serializable {

    @Id
    @SequenceGenerator(name = "esp_id", sequenceName = "seq_esp_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "esp_id")
    private int esp_id;
    private String esp_nome;
    private int esp_status;
    private int esp_tipo;

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

    public int getEsp_status() {
        return esp_status;
    }

    public void setEsp_status(int esp_status) {
        this.esp_status = esp_status;
    }

    public int getEsp_tipo() {
        return esp_tipo;
    }

    public void setEsp_tipo(int esp_tipo) {
        this.esp_tipo = esp_tipo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.esp_id;
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
        final Especificacao other = (Especificacao) obj;
        if (this.esp_id != other.esp_id) {
            return false;
        }
        return true;
    }

    public String toString() {
        return getEsp_nome();
    }

}
