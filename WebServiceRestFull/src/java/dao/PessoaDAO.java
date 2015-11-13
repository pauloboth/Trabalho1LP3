package dao;

import java.util.Date;
import model.Pessoa;
import java.util.List;
import org.hibernate.Session;
import util.HibernateUtil;

public class PessoaDAO {
    
    private Session session;
    
    public PessoaDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
    }
    
    public Session getSession() {
        if (session == null || !session.isOpen() || !session.isConnected()) {
            session = HibernateUtil.getSessionFactory().openSession();
        }
        return session;
    }
    
    public void save(Pessoa i) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        if (i.getPes_id() > 0) {
            session.update(i);
        } else {
            i.setPes_data(new Date());
            session.save(i);
        }
        session.getTransaction().commit();
        session.close();
    }
    
    public void delete(Pessoa i) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(i);
        session.getTransaction().commit();
        session.close();
    }
    
    public Pessoa findById(int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Pessoa m = (Pessoa) session.get(Pessoa.class, id);
        session.close();
        return m;
    }
    
    public List<Pessoa> findAll() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Pessoa> ls = session.createQuery("from Pessoa").list();
        session.close();
        return ls;
    }
    
}
