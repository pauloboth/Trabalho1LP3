package bean;

import dao.PessoaDAO;
import dao.ProdutoDAO;
import dao.PropostaDAO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import model.Pessoa;
import model.Produto;
import model.Proposta;

@ManagedBean
public class PropostaBean {

    private DataModel propostas;
    private PropostaDAO dao = new PropostaDAO();
    private Proposta proposta = new Proposta();
    private PessoaDAO pesDAO = new PessoaDAO();
    private List<Pessoa> lsPessoa = new ArrayList<>();
    private ProdutoDAO proDAO = new ProdutoDAO();
    private List<Produto> lsProduto = new ArrayList<>();

    public PropostaBean() {
    }

    public DataModel getPropostas() {
        this.propostas = new ListDataModel(dao.findAll());
        return propostas;
    }

    public void setPropostas(DataModel propostas) {
        this.propostas = propostas;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public void setProposta(Proposta proposta) {
        this.proposta = proposta;
    }

    public String edit(Proposta i) {
        proposta = (Proposta) propostas.getRowData();
        return "propostafrm";
    }

    public String delete(Proposta i) {
        try {
            dao.delete(i);
        } catch (Exception e) {
        }
        return "propostalst";
    }

    public String salvar() {
        dao.save(proposta);
        return "propostalst";
    }

    public String listar() {
        return "propostalst";
    }

    public List<Pessoa> getLsPessoa() {
        lsPessoa = pesDAO.findAll();
        return lsPessoa;
    }

    public List<Produto> getLsProduto() {
        lsProduto = proDAO.findAll();
        return lsProduto;
    }

}
