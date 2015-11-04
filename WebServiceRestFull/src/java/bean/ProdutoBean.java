package bean;

import dao.CategoriaDAO;
import dao.EspecificacaoDAO;
import dao.ProdutoDAO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import model.Categoria;
import model.Especificacao;
import model.Produto;
import model.ProdutoEspecificacao;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class ProdutoBean {

    private Produto produto = new Produto();
    private ProdutoDAO dao = new ProdutoDAO();
    private CategoriaDAO catDAO = new CategoriaDAO();
    private EspecificacaoDAO espDAO = new EspecificacaoDAO();

    private List<Categoria> lsCategorias = new ArrayList<>();
    private List<Especificacao> lsEspecificacoes = new ArrayList<>();
    private List<Especificacao> lsEspecificacoesAll = new ArrayList<>();
    private Especificacao especificacao = new Especificacao();

    private DataModel produtos;
    private UploadedFile image;

    public UploadedFile getImage() {
        return image;
    }

    public void setImage(UploadedFile image) {
        this.image = image;
    }

    public ProdutoBean() {
    }

    public DataModel getProdutos() {
        ClearSession();
        this.produtos = new ListDataModel(dao.findAll());
        return produtos;
    }

    public void setProdutos(DataModel i) {
        this.produtos = i;
    }

    public String edit(Produto i) {
        ClearSession();
        produto = dao.findEdit(i.getPro_id());
        return "produtofrm";
    }

    public String delete(Produto i) {
        try {
            dao.delete(i);
        } catch (Exception e) {
        }
        return "produtolst";
    }

    public String salvar() {
        dao.save(produto);
        ClearSession();
        return "produtolst";
    }

    public String listar() {
        return "produtolst";
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Categoria> getLsCategorias() {
        lsCategorias = catDAO.findAll();
        return lsCategorias;
    }

    public List<Especificacao> getLsEspecificacoes() {
        lsEspecificacoes = espDAO.findAll();
        lsEspecificacoesAll = lsEspecificacoes;
        ReloadEspecificacoes();
        return lsEspecificacoes;
    }

    private void ReloadEspecificacoes() {
        lsEspecificacoes = new ArrayList<>();
        if (lsEspecificacoesAll != null && !lsEspecificacoesAll.isEmpty()) {
            for (Especificacao e : lsEspecificacoesAll) {
                boolean bAdd = true;
                if (produto != null && produto.getLsProdutoEspecificacao() != null) {
                    for (ProdutoEspecificacao pe : produto.getLsProdutoEspecificacao()) {
                        if (e.getEsp_id() == pe.getEspecificacao().getEsp_id()) {
                            bAdd = false;
                        }
                    }
                }
                if (bAdd) {
                    lsEspecificacoes.add(e);
                }
            }
        }
    }

    public void removeEsp(ProdutoEspecificacao pe) {
        if (produto != null && produto.getLsProdutoEspecificacao() != null) {
            produto.getLsProdutoEspecificacao().remove(pe);
        }
    }

    public void addEspecificacao() {
        if (especificacao != null) {
            if (produto == null) {
                produto = new Produto();
            }
            if (produto.getLsProdutoEspecificacao() == null) {
                produto.setLsProdutoEspecificacao(new ArrayList<ProdutoEspecificacao>());
            }
            if (especificacao == null) {
                especificacao = new Especificacao();
            }
            boolean bAdd = true;
            for (ProdutoEspecificacao pe : produto.getLsProdutoEspecificacao()) {
                if (pe.getEspecificacao().getEsp_id() == especificacao.getEsp_id()) {
                    bAdd = false;
                }
            }
            if (bAdd) {
                ProdutoEspecificacao pe = new ProdutoEspecificacao();
                pe.setEspecificacao(especificacao);
                pe.setProduto(produto);
                produto.getLsProdutoEspecificacao().add(pe);
            }
            ReloadEspecificacoes();
        }
    }

    public Especificacao getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(Especificacao especificacao) {
        this.especificacao = especificacao;
    }

    private void ClearSession() {
        this.lsCategorias = new ArrayList<>();
        this.lsEspecificacoes = new ArrayList<>();
        this.lsEspecificacoesAll = new ArrayList<>();
        this.especificacao = new Especificacao();
        this.produto = new Produto();
    }

}
