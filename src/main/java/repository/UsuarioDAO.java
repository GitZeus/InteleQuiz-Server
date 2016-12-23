package repository;

import java.util.List;
import model.entity.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class UsuarioDAO {

    @Autowired
    private SessionFactory sessionFactory;
    private Session session;

    public Usuario getUsuarioById(Usuario u) {
        session = sessionFactory.getCurrentSession();
        return session.get(Usuario.class, Integer.parseInt(u.getLogin()));
    }

    public Usuario getUsuarioByLoginSenha(Usuario u) {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT u FROM Usuario as u WHERE u.login = :login AND u.senha = :senha");
        query.setParameter("login", u.getLogin());
        query.setParameter("senha", u.getSenha());
        return (Usuario) query.uniqueResult();
    }
    
    public List<Usuario> listUsuario(){
        session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Usuario").list();
    }
    
    public void saveUsuario(Usuario u){
        session = sessionFactory.getCurrentSession();
        session.save(u);
    }
    
    public void updateUsuario(Usuario u){
        session = sessionFactory.getCurrentSession();
        session.update(u);        
    }
    
    public void deleteUsuario(Usuario u){
        System.out.println(u);
        session = sessionFactory.getCurrentSession();
        session.delete(u);
    }
}