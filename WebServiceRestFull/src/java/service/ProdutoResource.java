package service;

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

    ProdutoDAO dao = new ProdutoDAO();

    public ProdutoResource() {
    }

    @GET
//     @Path("SelectAllProduto")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Produto> SelectAll() {
        return dao.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Produto SelectOne(@PathParam("id") Integer i) {
        return dao.findById(i);
    }

    @POST
    @Path("Insert")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean Insert(Produto p) {
        dao.save(p);
        return true;
    }

    @POST
    @Path("Delete/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean Delete(@PathParam("id") Integer i) {
        Produto p = new Produto();
        p.setPro_id(i);
        dao.delete(p);
        return true;
    }

    @POST
    @Path("Update")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean Update(Produto p) {
        dao.save(p);
        return true;
    }
}
