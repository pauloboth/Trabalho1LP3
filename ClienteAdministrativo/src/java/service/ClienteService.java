package service;

import java.util.List;
import javax.jws.WebService;
import model.Produto;

@WebService
public interface ClienteService {

    public boolean Insert(String descrição);

    public boolean InsertProduct(Produto p);

    public List<Produto> SelectAll();

    public Produto SelectOne(int i);

    public boolean Delete(int i);

    public boolean Update(Produto p);
}
