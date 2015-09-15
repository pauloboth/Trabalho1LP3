package service;

import dao.CategoriaDAO;
import dao.ProdutoDAO;
import java.util.List;
import javax.jws.WebService;
import model.Categoria;
import model.Produto;

@WebService(serviceName = "ClienteService")
public class ClienteServiceImpl implements ClienteService {

    @Override
    public List<Produto> SelectAllProduto() {
        ProdutoDAO dao = new ProdutoDAO();
        return dao.findAll();
    }

    @Override
    public Produto SelectOneProduto(int i) {
        ProdutoDAO dao = new ProdutoDAO();
        return dao.findById(i);
    }

    @Override
    public boolean InsertProduto(Produto p) {
        ProdutoDAO dao = new ProdutoDAO();
        dao.insert(p);
        return true;
    }

    @Override
    public boolean DeleteProduto(int i) {
        ProdutoDAO dao = new ProdutoDAO();
        Produto p = new Produto();
        p.setPro_id(i);
        dao.delete(p);
        return true;
    }

    @Override
    public boolean UpdateProduto(Produto p) {
        ProdutoDAO dao = new ProdutoDAO();
        dao.update(p);
        return true;
    }

    @Override
    public List<Categoria> SelectAllCategoria() {
        CategoriaDAO dao = new CategoriaDAO();
        return dao.findAll();
    }

    @Override
    public Categoria SelectOneCategoria(int i) {
        CategoriaDAO dao = new CategoriaDAO();
        return dao.findById(i);
    }

    @Override
    public boolean InsertCategoria(Categoria c) {
        CategoriaDAO dao = new CategoriaDAO();
        dao.insert(c);
        return true;
    }

    @Override
    public boolean DeleteCategoria(int i) {
        CategoriaDAO dao = new CategoriaDAO();
        Categoria c = new Categoria();
        c.setCat_id(i);
        dao.delete(c);
        return true;
    }

    @Override
    public boolean UpdateCategoria(Categoria c) {
        CategoriaDAO dao = new CategoriaDAO();
        dao.update(c);
        return true;
    }
}
