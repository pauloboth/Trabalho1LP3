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

    public void insert(Produto i) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(i);
        session.getTransaction().commit();
        session.close();
    }

    public void update(Produto i) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(i);
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

    public Produto findEdit(int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Produto p = (Produto) session.createQuery("select p from Produto p "
                + "left outer join fetch p.lsProdutoEspecificacao pe "
                + "where p.pro_id = :p")
                .setParameter("p", id).uniqueResult();
        session.close();
        return p;
    }
}
