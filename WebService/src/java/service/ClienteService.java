package service;

import java.util.List;
import javax.jws.WebService;
import model.Categoria;
import model.Produto;

@WebService
public interface ClienteService {

    public boolean InsertProduto(Produto p);

    public boolean InsertCategoria(Categoria c);

    public List<Produto> SelectAllProduto();

    public List<Categoria> SelectAllCategoria();

    public Produto SelectOneProduto(int i);

    public Categoria SelectOneCategoria(int i);

    public boolean DeleteProduto(int i);

    public boolean DeleteCategoria(int i);

    public boolean UpdateProduto(Produto p);

    public boolean UpdateCategoria(Categoria c);
}
