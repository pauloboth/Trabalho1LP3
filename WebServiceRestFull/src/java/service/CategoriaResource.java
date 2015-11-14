package service;

import dao.CategoriaDAO;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Categoria;

@Path("/Categoria")
public class CategoriaResource {

    CategoriaDAO dao = new CategoriaDAO();

    public CategoriaResource() {
    }

    @GET
//     @Path("SelectAllProduto")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Categoria> SelectAll() {
        List<Categoria> lsCat = dao.findAll();
        if (lsCat == null) {
            lsCat = new ArrayList<>();
        }
        return lsCat;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Categoria SelectOne(@PathParam("id") Integer i) {
        Categoria c = dao.findById(i);
        if (c == null) {
            c = new Categoria();
        }
        return c;
    }

    @POST
    @Path("Insert")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean Insert(Categoria c) {
        dao.save(c);
        return true;
    }

    @POST
    @Path("Delete/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean Delete(@PathParam("id") Integer i) {
        Categoria c = new Categoria();
        c.setCat_id(i);
        dao.delete(c);
        return true;
    }

    @POST
    @Path("Update")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean Update(Categoria c) {
        dao.save(c);
        return true;
    }
}
