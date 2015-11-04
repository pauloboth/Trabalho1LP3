package dao;

import model.Produto;
import java.util.List;
import org.hibernate.Session;
import util.HibernateUtil;

public class ProdutoDAO {

    private Session session;

    public ProdutoDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public Session getSession() {
        if (session == null || !session.isOpen() || !session.isConnected()) {
            session = HibernateUtil.getSessionFactory().openSession();
        }
        return session;
    }

    public void save(Produto i) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        if (i.getPro_id()> 0) {
            session.update(i);
        } else {
            session.save(i);
        }
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Produto i) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(i);
        session.getTransaction().commit();
        session.close();
    }

    public Produto findById(int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Produto m = (Produto) session.get(Produto.class, id);
        session.close();
        return m;
    }

    public List<Produto> findAll() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Produto> ls = session.createQuery("from Produto").list();
        session.close();
        return ls;
    }
}
