package gateway;

import model.application.ITQException;
import java.util.List;
import model.entity.Disciplina;
import model.entity.Tag;
import model.entity.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class GatewayDisciplina {

    @Autowired
    private SessionFactory sessionFactory;
    private Session session;

    public List<Disciplina> listDisciplinasByProfessor(Usuario u) throws ITQException {
        session = sessionFactory.getCurrentSession();
            return session.createQuery("FROM Disciplina").list();
    }
    
    public List<Tag> listTagsByDisciplina(Disciplina d) throws ITQException {
        session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT t FROM Tag t WHERE t.nome = :nome").setParameter("nome", "Programação").list();
    }
    
    
}
