package persistencia;

import entidade.Disciplina;
import entidade.Professor;
import entidade.Questao;
import entidade.Tema;
import entidade.Usuario;
import util.ITQException;
import java.util.List;
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

    public List<Disciplina> listDisciplinasByProfessor(Professor p) throws ITQException {
        try {
            session = sessionFactory.openSession();
            Professor professor = session.get(Professor.class, p.getMatricula());
            return professor.getDisciplinas();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ITQException(e.getMessage());
        }
    }

    public List<Tema> listTagsByDisciplina(Disciplina d) throws ITQException {
        session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT t FROM Tag t WHERE t.nome = :nome").setParameter("nome", "Programação").list();
    }

    public List<Questao> listQuestoesByTag() throws ITQException {
        session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Questao").list();
    }

}
