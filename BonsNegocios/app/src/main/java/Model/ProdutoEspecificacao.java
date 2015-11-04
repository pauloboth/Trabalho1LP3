package Model;


public class ProdutoEspecificacao {
    private Produto produto;
    private  Especificacao especificacao;
    private String prs_descricao;


    public ProdutoEspecificacao(){}

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

    @Override
    public String toString() {
        return getEspecificacao().getEsp_nome()+": "+getPrs_descricao();
    }
}
