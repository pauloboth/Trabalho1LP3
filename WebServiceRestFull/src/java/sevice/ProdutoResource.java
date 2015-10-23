package sevice;

import dao.ProdutoDAO;
import java.util.List;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Produto;

@Path("/Produto")
public class ProdutoResource {

    public ProdutoResource() {
    }

    @GET
//     @Path("SelectAllProduto")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Produto> SelectAll() {
        ProdutoDAO dao = new ProdutoDAO();
        return dao.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Produto SelectOne(@PathParam("id") Integer i) {
        ProdutoDAO dao = new ProdutoDAO();
        return dao.findById(i);
    }

    @POST
    @Path("Insert")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean Insert(Produto p) {
        ProdutoDAO dao = new ProdutoDAO();
        dao.insert(p);
        return true;
    }

    @POST
    @Path("Delete/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean Delete(@PathParam("id") Integer i) {
        ProdutoDAO dao = new ProdutoDAO();
        Produto p = new Produto();
        p.setPro_id(i);
        dao.delete(p);
        return true;
    }

    @POST
    @Path("Update")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean Update(Produto p) {
        ProdutoDAO dao = new ProdutoDAO();
        dao.update(p);
        return true;
    }
}
