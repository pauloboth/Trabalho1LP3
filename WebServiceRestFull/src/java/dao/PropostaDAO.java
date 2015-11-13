package dao;

import java.util.Date;
import model.Proposta;
import java.util.List;
import org.hibernate.Session;
import util.HibernateUtil;

public class PropostaDAO {
    
    private Session session;
    
    public PropostaDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
    }
    
    public Session getSession() {
        if (session == null || !session.isOpen() || !session.isConnected()) {
            session = HibernateUtil.getSessionFactory().openSession();
        }
        return session;
    }
    
    public void save(Proposta i) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        if (i.getPrs_id() > 0) {
            session.update(i);
        } else {
            i.setPrs_data(new Date());
            session.save(i);
        }
        session.getTransaction().commit();
        session.close();
    }
    
    public void delete(Proposta i) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(i);
        session.getTransaction().commit();
        session.close();
    }
    
    public Proposta findById(int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Proposta m = (Proposta) session.get(Proposta.class, id);
        session.close();
        return m;
    }
    
    public List<Proposta> findAll() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Proposta> ls = session.createQuery("from Proposta").list();
        session.close();
        return ls;
    }
    
}
