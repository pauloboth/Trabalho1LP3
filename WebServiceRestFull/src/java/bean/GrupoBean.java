package bean;

import dao.GrupoDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import model.Grupo;

@ManagedBean
public class GrupoBean {

    private DataModel grupos;
    private GrupoDAO dao = new GrupoDAO();
    private Grupo grupo = new Grupo();

    public GrupoBean() {
    }

    public DataModel getGrupos() {
        this.grupos = new ListDataModel(dao.findAll());
        return grupos;
    }

    public void setGrupos(DataModel grupos) {
        this.grupos = grupos;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public String edit(Grupo i) {
        grupo = (Grupo) grupos.getRowData();
        return "grupofrm";
    }

    public String delete(Grupo i) {
        try {
            dao.delete(i);
        } catch (Exception e) {
        }
        return "grupolst";
    }

    public String salvar() {
        dao.save(grupo);
        return "grupolst";
    }

    public String listar() {
        return "grupolst";
    }

}
