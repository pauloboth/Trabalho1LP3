package bean;

import dao.CategoriaDAO;
import dao.EspecificacaoDAO;
import dao.ProdutoDAO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.imageio.ImageIO;
import model.Categoria;
import model.Especificacao;
import model.Produto;
import model.ProdutoEspecificacao;
import org.primefaces.event.FileUploadEvent;
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

    public void handleFileUpload(FileUploadEvent event) {
        try {
//            File file = new File("/faces/resources/productImages/" + event.getFile().getFileName());
            File file = new File(event.getFile().getFileName());
            InputStream inputStream = event.getFile().getInputstream();
            BufferedImage imagem = ImageIO.read(inputStream);
            ImageIO.write(imagem, "jpg", file);
//            OutputStream out = new FileOutputStream(file);
//            int read = 0;
//            byte[] bytes = new byte[1024];
//
//            while ((read = inputStream.read(bytes)) != -1) {
//                out.write(bytes, 0, read);
//            }
//            inputStream.close();
//            out.flush();
//            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setImage(UploadedFile image) {
        try {
            File targetFolder = new File("/faces/resources/productImages");
            InputStream inputStream = image.getInputstream();
            Random ran = new Random();
            String name = "";
            for (int i = 0; i < 10; i++) {
                name += ran.nextInt(10) + "";
            }
            name += ".jpg";
//            OutputStream out = new FileOutputStream(new File(targetFolder, name));
            OutputStream out = new FileOutputStream(new File(targetFolder,
                    image.getFileName()));
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            inputStream.close();
            out.flush();
            out.close();
            produto.setPro_imagem("/faces/productImages/" + name);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
