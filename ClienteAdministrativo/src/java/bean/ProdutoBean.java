package bean;

import com.sun.xml.rpc.client.dii.webservice.WebService;
import dao.CategoriaDAO;
import dao.ProdutoDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.xml.ws.WebServiceRef;
import model.Categoria;
import model.Produto;
import webService.ClienteService;

@javax.jws.WebService
@ManagedBean
public class ProdutoBean {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/172.20.156.161_8080/Seriplast/ClienteService.wsdl")
    private ClienteService service;
    private Produto produto = new Produto();
    private List<Categoria> lsCategorias;
    private DataModel produtos;

    public ProdutoBean() {
    }

    public DataModel getProdutos() {
        this.produtos = new ListDataModel(ConvertProduct(selectAllProduto()));
        return produtos;
    }

    public void setProdutos(DataModel i) {
        this.produtos = i;
    }

    public String edit(Produto i) {
        produto = (Produto) produtos.getRowData();
        return "produtofrm";
    }

    public String delete(Produto i) {
        try {
            deleteProduto(i.getPro_id());
        } catch (Exception e) {
        }
        return "produtolst";
    }

    public String salvar() {
        webService.Produto pro = new webService.Produto();
        pro.setProNome(produto.getPro_nome());
        pro.setProDescricao(produto.getPro_descricao());
        pro.setCategoria(pro.getCategoria());//(produto.getCategoria());
        pro.setProEstoque(produto.getPro_estoque());
        pro.setProStatus(produto.getPro_status());
        pro.setProTipo(produto.getPro_tipo());
        if (produto.getPro_id() > 0) {
            pro.setProId(produto.getPro_id());
            updateProduto(pro);
        } else {
            insertProduto(pro);
        }
        return "produtolst";
    }

    public String listar() {
        return "produtolst";
    }

    public List<Produto> ConvertProduct(List<webService.Produto> lsProductServer) {

        List<Produto> lsProdutos = new ArrayList<>();
        for (webService.Produto servProd : lsProductServer) {
            Produto pro = new Produto();
            pro.setPro_nome(servProd.getProNome());
            pro.setPro_descricao(servProd.getProDescricao());
            
            webService.Categoria cat = servProd.getCategoria();
            Categoria categoria = new Categoria();
            categoria.setCat_id(cat.getCatId());
            categoria.setCat_nome(cat.getCatNome());
            pro.setCategoria(categoria);//(produto.getCategoria());
            
            pro.setPro_estoque(servProd.getProEstoque());
            pro.setPro_status(servProd.getProStatus());
            pro.setPro_tipo(servProd.getProTipo());
            lsProdutos.add(pro);
        }
        return lsProdutos;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Categoria> getLsCategorias() {
        lsCategorias.clear();
        Categoria categoria;
        for (webService.Categoria CAT : selectAllCategoria()) {
            categoria = new Categoria();
            categoria.setCat_nome(CAT.getCatNome());
            categoria.setCat_id(CAT.getCatId());
            categoria.setCat_descricao(CAT.getCatDescricao());
            lsCategorias.add(categoria);
        }
        return lsCategorias;
    }

    public void setLsCategorias(List<Categoria> lsCategorias) {
        this.lsCategorias = lsCategorias;
    }

    private boolean insertProduto(webService.Produto arg0) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        webService.ClienteServiceImpl port = service.getClienteServiceImplPort();
        return port.insertProduto(arg0);
    }

    private boolean updateProduto(webService.Produto arg0) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        webService.ClienteServiceImpl port = service.getClienteServiceImplPort();
        return port.updateProduto(arg0);
    }

    private java.util.List<webService.Produto> selectAllProduto() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        webService.ClienteServiceImpl port = service.getClienteServiceImplPort();
        return port.selectAllProduto();
    }

    private boolean deleteProduto(int arg0) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        webService.ClienteServiceImpl port = service.getClienteServiceImplPort();
        return port.deleteProduto(arg0);
    }

    private java.util.List<webService.Categoria> selectAllCategoria() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        webService.ClienteServiceImpl port = service.getClienteServiceImplPort();
        return port.selectAllCategoria();
    }

}
