package bean;

import dao.CategoriaDAO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.jws.WebService;
import javax.xml.ws.WebServiceRef;
import model.Categoria;
import webService.ClienteService;

@WebService
@ManagedBean
public class CategoriaBean {
    
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/172.20.156.161_8080/Seriplast/ClienteService.wsdl")
    private ClienteService service;
    
    private Categoria categoria = new Categoria();
    //  private CategoriaDAO dao = new CategoriaDAO();
    private DataModel categorias;
    
    public CategoriaBean() {
    }
    
    public DataModel getCategorias() {
        this.categorias = new ListDataModel(ConvertCategory(selectAllCategoria()));
        return categorias;
    }
    
    public void setCategorias(DataModel i) {
        this.categorias = i;
    }
    
    public String edit(Categoria i) {
        categoria = (Categoria) categorias.getRowData();
        return "categoriafrm";
    }
    
    public String delete(Categoria i) {
        try {
            deleteCategoria(i.getCat_id());
        } catch (Exception e) {
        }
        return "categorialst";
    }
    
    public String salvar() {
        webService.Categoria CAT = new webService.Categoria();
        CAT.setCatNome(categoria.getCat_nome());
        CAT.setCatDescricao(categoria.getCat_descricao());
        if (categoria.getCat_id() > 0) {
            CAT.setCatId(categoria.getCat_id());
            updateCategoria(CAT);
        } else {
            insertCategoria(CAT);
        }
        return "categorialst";
    }
    
    public String listar() {
        return "categorialst";
    }
    
    public Categoria getCategoria() {
        return categoria;
    }
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    private List<Categoria> ConvertCategory(List<webService.Categoria> CategoryServer) {
        List<Categoria> lsCategory = new ArrayList<>();
        if (CategoryServer != null) {
            Categoria categoria;
            for (webService.Categoria server : CategoryServer) {
                categoria = new Categoria();
                categoria.setCat_nome(server.getCatNome());
                categoria.setCat_id(server.getCatId());
                categoria.setCat_descricao(server.getCatDescricao());
                lsCategory.add(categoria);
            }
        }
        return lsCategory;
    }
    
    private java.util.List<webService.Categoria> selectAllCategoria() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        webService.ClienteServiceImpl port = service.getClienteServiceImplPort();
        return port.selectAllCategoria();
    }
    
    private boolean insertCategoria(webService.Categoria arg0) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        webService.ClienteServiceImpl port = service.getClienteServiceImplPort();
        return port.insertCategoria(arg0);
    }
    
    private boolean updateCategoria(webService.Categoria arg0) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        webService.ClienteServiceImpl port = service.getClienteServiceImplPort();
        return port.updateCategoria(arg0);
    }
    
    private webService.Categoria selectOneCategoria(int arg0) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        webService.ClienteServiceImpl port = service.getClienteServiceImplPort();
        return port.selectOneCategoria(arg0);
    }
    
    private boolean deleteCategoria(int arg0) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        webService.ClienteServiceImpl port = service.getClienteServiceImplPort();
        return port.deleteCategoria(arg0);
    }
    
}
