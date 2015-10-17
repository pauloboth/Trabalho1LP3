package bean;

import dao.CategoriaDAO;
import dao.EspecificacaoDAO;
import dao.ProdutoDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import model.Categoria;
import model.Especificacao;
import model.Produto;
import model.ProdutoEspecificacao;

@ManagedBean
@SessionScoped
public class ProdutoBean {

    private Produto produto = new Produto();
    private ProdutoDAO dao = new ProdutoDAO();
    private CategoriaDAO catDAO = new CategoriaDAO();
    private List<Categoria> lsCategorias;
    private EspecificacaoDAO espDAO = new EspecificacaoDAO();
    private List<Especificacao> lsEspecificacao = new ArrayList<>();
    private List<Especificacao> lsEspecificacaoAll = new ArrayList<>();
    private Especificacao especificacao = new Especificacao();

    private DataModel produtos;

    public ProdutoBean() {
    }

    public DataModel getProdutos() {
        this.produtos = new ListDataModel(dao.findAll());
        clearSession();
        return produtos;
    }

    public void setProdutos(DataModel i) {
        this.produtos = i;
    }

    public String edit(Produto p) {
        this.produto = dao.findById(p.getPro_id());
        return "produtofrm";
    }

    public String delete(Produto p) {
        try {
            dao.delete(p);
        } catch (Exception e) {
        }
        clearSession();
        return "produtolst";
    }

    public String salvar() {
        if (this.produto.getPro_id() > 0) {
            dao.update(this.produto);
        } else {
            this.produto.setPro_cadastro(new Date());
            dao.insert(this.produto);
        }
        clearSession();
        return "produtolst";
    }

    public String listar() {
        return "produtolst";
    }

    public Produto getProduto() {
        return this.produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Categoria> getLsCategorias() {
        this.lsCategorias = catDAO.findAll();
        return lsCategorias;
    }

    public void setLsCategorias(List<Categoria> lsCategorias) {
        this.lsCategorias = lsCategorias;
    }

    public List<Especificacao> getLsEspecificacao() {
        lsEspecificacao = espDAO.findAll();
        lsEspecificacaoAll = lsEspecificacao;
        return lsEspecificacao;
    }

    public void setLsEspecificacao(List<Especificacao> lsEspecificacao) {
        this.lsEspecificacao = lsEspecificacao;
    }

    public Especificacao getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(Especificacao especificacao) {
        this.especificacao = especificacao;
    }

    public void addEspecificacao() {
        if (especificacao != null) {
            if (produto.getLsProdutoEspecificacao() == null) {
                produto.setLsProdutoEspecificacao(new ArrayList<ProdutoEspecificacao>());
            }
            boolean bAdd = true;
            for (ProdutoEspecificacao pe : produto.getLsProdutoEspecificacao()) {
                if (pe.getEspecificacao().getEsp_id() == especificacao.getEsp_id()) {
                    bAdd = false;
                }
            }
            if (bAdd) {
                ProdutoEspecificacao pe = new ProdutoEspecificacao();
                pe.setProduto(produto);
                pe.setEspecificacao(especificacao);
                produto.getLsProdutoEspecificacao().add(pe);
            }
            //reloadMaquinas();
        }
    }

    public void removeEspecificacao(ProdutoEspecificacao pm) {
        this.produto.getLsProdutoEspecificacao().remove(pm);
        //reloadMaquinas();
    }

    private void clearSession() {
        this.lsCategorias = new ArrayList<>();
        this.lsEspecificacao = new ArrayList<>();
        this.lsEspecificacaoAll = new ArrayList<>();
        this.especificacao = new Especificacao();
        this.produto = new Produto();
    }
}
