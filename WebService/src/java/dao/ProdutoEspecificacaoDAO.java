package dao;

import model.ProdutoEspecificacao;
import java.util.List;
import org.hibernate.Session;
import util.HibernateUtil;

public class ProdutoEspecificacaoDAO {

    private Session session;

    public ProdutoEspecificacaoDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public Session getSession() {
        if (session == null || !session.isOpen() || !session.isConnected()) {
            session = HibernateUtil.getSessionFactory().openSession();
        }
        return session;
    }

    public void insert(ProdutoEspecificacao i) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(i);
        session.getTransaction().commit();
        session.close();
    }

    public void update(ProdutoEspecificacao i) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(i);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(ProdutoEspecificacao i) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(i);
        session.getTransaction().commit();
        session.close();
    }

    public ProdutoEspecificacao findById(int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        ProdutoEspecificacao m = (ProdutoEspecificacao) session.load(ProdutoEspecificacao.class, id);
        session.close();
        return m;
    }

    public List<ProdutoEspecificacao> findAll() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<ProdutoEspecificacao> ls = session.createQuery("from ProdutoEspecificacao").list();
        return ls;
    }
}
