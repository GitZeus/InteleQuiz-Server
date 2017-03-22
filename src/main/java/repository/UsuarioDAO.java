package repository;

import model.application.ITQException;
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

    public Usuario getUsuarioByLoginSenha(Usuario u) throws ITQException {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT u FROM usuario u WHERE u.login = :login AND u.senha = :senha");
        query.setParameter("login", u.getLogin());
        query.setParameter("senha", u.getSenha());
        u = (Usuario) query.uniqueResult();
        
        if(u != null){
            return u;
        }else{
            throw new ITQException("Login ou Senha incorretos");
        }
        
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
        session = sessionFactory.getCurrentSession();
        session.delete(u);
    }
}