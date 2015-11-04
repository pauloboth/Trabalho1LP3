package sevice;

import dao.EspecificacaoDAO;
import java.util.List;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Especificacao;

@Path("/Especificacao")
public class EspecificacaoResource {

    EspecificacaoDAO dao = new EspecificacaoDAO();

    public EspecificacaoResource() {
    }

    @GET
//  @Path("SelectAllProduto")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Especificacao> SelectAll() {
        return dao.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Especificacao SelectOne(@PathParam("id") Integer i) {
        return dao.findById(i);
    }

    @POST
    @Path("Insert")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean Insert(Especificacao e) {
        dao.save(e);
        return true;
    }

    @POST
    @Path("Delete/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean Delete(@PathParam("id") Integer i) {
        Especificacao e = new Especificacao();
        e.setEsp_id(i);
        dao.delete(e);
        return true;
    }

    @POST
    @Path("Update")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean Update(Especificacao e) {
        dao.save(e);
        return true;
    }
}
