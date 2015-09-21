package dao;

import model.ProdutoEspecificacao;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.expression.impl.ThisExpressionResolver;
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
        Transaction t = session.beginTransaction();
        session.save(i);
        t.commit();
//        session.close();
    }

    public void update(ProdutoEspecificacao i) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.merge(i);
        t.commit();
//        session.close();
    }

    public void delete(ProdutoEspecificacao i) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(i);
        t.commit();
//        session.close();
    }

    public ProdutoEspecificacao findById(int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        ProdutoEspecificacao m = (ProdutoEspecificacao) session.load(ProdutoEspecificacao.class, id);
//        session.close();
        return m;
    }

    public List<ProdutoEspecificacao> findAll(int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        String sql = "";
        if (id != 0) {
            sql += " and pro_id = " + id;
        }
        List<ProdutoEspecificacao> ls = session.createQuery("from ProdutoEspecificacao where 1 = 1 " + sql).list();
        return ls;
    }

    public void deleteAll(int pro_id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        List<ProdutoEspecificacao> ls = session.createQuery("from ProdutoEspecificacao where pro_id = " + pro_id).list();
        for (ProdutoEspecificacao prs : ls) {
            session.delete(prs);
        }
        t.commit();
//        session.close();
    }

    public void insertList(List<ProdutoEspecificacao> i) {
        Session s = this.getSession();
        Transaction t = s.getTransaction();
        if (!t.isActive()) {
            t = s.beginTransaction();
        }
        if (i != null && !i.isEmpty()) {
            for (ProdutoEspecificacao prs : i) {
                s.save(prs);
            }
        }
        t.commit();
        s.close();
    }
}
