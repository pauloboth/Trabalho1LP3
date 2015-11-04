package Model;


public class Especificacao {
    private int esp_id;
    private String esp_nome;

    public Especificacao(){}

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

    @Override
    public String toString() {
        return getEsp_nome();
    }
}
