package bean;

import dao.GrupoDAO;
import dao.PessoaDAO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import model.Grupo;
import model.Pessoa;

@ManagedBean
public class PessoaBean {

    private DataModel pessoas;

    private PessoaDAO dao = new PessoaDAO();
    private Pessoa pessoa = new Pessoa();
    private GrupoDAO gruDAO = new GrupoDAO();
    private List<Grupo> lsGrupo = new ArrayList<>();

    public PessoaBean() {
    }

    public DataModel getPessoas() {
        this.pessoas = new ListDataModel(dao.findAll());
        return pessoas;
    }

    public void setPessoas(DataModel pessoas) {
        this.pessoas = pessoas;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String edit(Pessoa i) {
        pessoa = (Pessoa) pessoas.getRowData();
        return "pessoafrm";
    }

    public String delete(Pessoa i) {
        try {
            dao.delete(i);
        } catch (Exception e) {
        }
        return "pessoalst";
    }

    public String salvar() {
        dao.save(pessoa);
        return "pessoalst";
    }

    public String listar() {
        return "pessoalst";
    }

    public List<Grupo> getLsGrupo() {
        lsGrupo = gruDAO.findAll();
        return lsGrupo;
    }

}
