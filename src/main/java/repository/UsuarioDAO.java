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
        Query query = session.createQuery("SELECT u.nome FROM Usuario as u WHERE u.cod_usuario = :cod_usuario");
        query.setParameter("cod_usuario", u.getCod_usuario());
        List<Usuario> usuarios = query.list();
        if(usuarios.size() == 1){
            return usuarios.get(0);
        }else{
            return null;
        }
    }
    
    public List<Usuario> listUsuario(){
        session = sessionFactory.getCurrentSession();
        List<Usuario> usuarios = session.createQuery("FROM Usuario").list();
        return usuarios;
    }
}