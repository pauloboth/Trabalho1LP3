package bean;

import dao.EspecificacaoDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import model.Especificacao;

@ManagedBean
public class EspecificacaoBean {

    private Especificacao especificacao = new Especificacao();
    private EspecificacaoDAO dao = new EspecificacaoDAO();
    private DataModel especificacoes;

    public EspecificacaoBean() {
    }

    public DataModel getEspecificacoes() {
        this.especificacoes = new ListDataModel(dao.findAll());
        return especificacoes;
    }

    public void setEspecificacoes(DataModel i) {
        this.especificacoes = i;
    }

    public String edit(Especificacao i) {
        especificacao = (Especificacao) especificacoes.getRowData();
        return "especificacaofrm";
    }

    public String delete(Especificacao i) {
        try {
            dao.delete(i);
        } catch (Exception e) {
        }
        return "especificacaolst";
    }

    public String salvar() {
        dao.save(especificacao);
        return "especificacaolst";
    }

    public String listar() {
        return "especificacaolst";
    }

    public Especificacao getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(Especificacao especificacao) {
        this.especificacao = especificacao;
    }

}
