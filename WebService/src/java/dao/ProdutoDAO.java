package dao;

import model.Produto;
import java.util.List;
import model.ProdutoEspecificacao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import util.Utilidades;

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
        Session s = this.getSession();
        Transaction t = s.getTransaction();
        if (!t.isActive()) {
            t = s.beginTransaction();
        }
        Produto pro = new Produto();
        pro.setCategoria(i.getCategoria());
        pro.setPro_cadastro(i.getPro_cadastro());
        pro.setPro_descricao(i.getPro_descricao());
        pro.setPro_estoque(i.getPro_estoque());
        pro.setPro_nome(i.getPro_nome());
        pro.setPro_preco(i.getPro_preco());
        pro.setPro_status(i.getPro_status());
        pro.setPro_tipo(i.getPro_tipo());
        s.save(pro);
        if (i.getProdutoEsp() != null && !i.getProdutoEsp().isEmpty()) {
            for (ProdutoEspecificacao prs : i.getProdutoEsp()) {
                ProdutoEspecificacao Prs = new ProdutoEspecificacao();
                Prs.setEspecificacao(prs.getEspecificacao());
                Prs.setPrs_descricao(prs.getPrs_descricao());
                Prs.setProduto(pro);
                s.save(Prs);
            }
        }
        t.commit();
        s.close();
    }

    public void update(Produto i) {
        Session s = this.getSession();
        Transaction t = s.getTransaction();
        if (!t.isActive()) {
            t = s.beginTransaction();
        }

        Produto pro = new Produto();
        pro.setCategoria(i.getCategoria());
        pro.setPro_cadastro(i.getPro_cadastro());
        pro.setPro_descricao(i.getPro_descricao());
        pro.setPro_estoque(i.getPro_estoque());
        pro.setPro_nome(i.getPro_nome());
        pro.setPro_preco(i.getPro_preco());
        pro.setPro_status(i.getPro_status());
        pro.setPro_tipo(i.getPro_tipo());
        pro.setPro_id(i.getPro_id());
        pro.setProdutoEsp(i.getProdutoEsp());
        s.merge(i);
        if (pro.getProdutoEsp() != null && !pro.getProdutoEsp().isEmpty()) {
            for (ProdutoEspecificacao prs : pro.getProdutoEsp()) {
                ProdutoEspecificacao Prs = new ProdutoEspecificacao();
                Prs.setEspecificacao(prs.getEspecificacao());
                Prs.setPrs_descricao(prs.getPrs_descricao());
                Prs.setProduto(pro);
                s.save(Prs);
            }
        }

        t.commit();
        s.close();
    }

    public void delete(Produto i) {
        //s.createQuery("delete from ProdutoEspecificacao where pro_id = " + pro.getPro_id());
//        Session s = this.getSession();
//        Transaction t = s.getTransaction();
//        if (!t.isActive()) {
//            t = s.beginTransaction();
//        }

        Session s = this.getSession();
        Utilidades.setSession(s);
        Transaction t = s.beginTransaction();
        s.delete(i);
        t.commit();
        s.close();
    }

    public Produto findById(int id) {
        Session s = this.getSession();
        Produto m = (Produto) s.load(Produto.class, id);
        List<ProdutoEspecificacao> lsPrs = s.createQuery("from ProdutoEspecificacao where pro_id = " + m.getPro_id()).list();
        m.setProdutoEsp(lsPrs);
        //session.close();
        return m;
    }

    public List<Produto> findAll() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Produto> ls = session.createQuery("from Produto").list();
//        session.close();
        return ls;
    }

}
