package bean;

import dao.CategoriaDAO;
import dao.EspecificacaoDAO;
import dao.ProdutoDAO;
import dao.ProdutoEspecificacaoDAO;
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
    private ProdutoEspecificacaoDAO prsDAO = new ProdutoEspecificacaoDAO();
    private EspecificacaoDAO espDAO = new EspecificacaoDAO();
    private List<Especificacao> lsEspecificacoes;

    private DataModel produtos;

    public ProdutoBean() {
    }

    public DataModel getProdutos() {
        this.produtos = new ListDataModel(dao.findAll());
        return produtos;
    }

    public void setProdutos(DataModel i) {
        this.produtos = i;
    }

    public String edit(Produto p) {
        this.produto = dao.findById(p.getPro_id());
        //this.produto = p;
        return "produtofrm";
    }

    public String New() {
        this.produto = new Produto();
        return "produtofrm";
    }

    public String delete(Produto p) {
        try {
            //prsDAO.deleteAll(p.getPro_id());
            dao.delete(p);
        } catch (Exception e) {
        }
        return "produtolst";
    }

    public String salvar() {
        if (this.produto.getPro_id() > 0) {
            List<ProdutoEspecificacao> lsPrs = this.produto.getProdutoEsp();
            for (ProdutoEspecificacao prs : this.produto.getProdutoEsp()) {
                prs.setProduto(this.produto);
                lsPrs.add(prs);
            }
            prsDAO.deleteAll(produto.getPro_id());
            prsDAO.insertList(lsPrs);
            dao.update(this.produto);
        } else {
            this.produto.setPro_cadastro(new Date());
            dao.insert(this.produto);
        }
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

    public List<Especificacao> getLsEspecificacoes() {
        this.lsEspecificacoes = espDAO.findAll();
        return lsEspecificacoes;
    }

    public void setLsEspecificacoes(List<Especificacao> lsEspecificacoes) {
        this.lsEspecificacoes = lsEspecificacoes;
    }

    public void addProdutoEsp() {
        if (this.produto == null) {
            this.produto = new Produto();
        }
        this.produto.addProdutoEsp();
    }

    public void deProdutoEsp(ProdutoEspecificacao p) {
        if (this.produto != null) {
            this.produto.deProdutoEsp(p);
        }
    }

}
