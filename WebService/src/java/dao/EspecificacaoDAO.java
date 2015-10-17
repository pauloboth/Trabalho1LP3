package dao;

import model.Especificacao;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class EspecificacaoDAO {

    private Session session;

    public EspecificacaoDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public Session getSession() {
        if (session == null || !session.isOpen() || !session.isConnected()) {
            session = HibernateUtil.getSessionFactory().openSession();
        }
        return session;
    }

    public void insert(Especificacao i) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(i);
        session.getTransaction().commit();
        session.close();
    }

    public void update(Especificacao i) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(i);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Especificacao i) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(i);
        session.getTransaction().commit();
        session.close();
    }

    public Especificacao findById(int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Especificacao m = (Especificacao) session.load(Especificacao.class, id);
        session.close();
        return m;
    }

    public List<Especificacao> findAll() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Especificacao> ls = session.createQuery("from Especificacao").list();
        session.close();
        return ls;
    }

}
