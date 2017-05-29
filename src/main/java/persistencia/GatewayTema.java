package persistencia;

import entidade.Tema;
import java.util.List;
import org.hibernate.Query;
import util.ITQException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class GatewayTema {

    @Autowired(required = false)
    private SessionFactory sessionFactory;
    private Session session;

    public List<Tema> listTemasByDisciplinaByProfessor(String matricula_professor, int disciplina_id) throws ITQException {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Tema t WHERE t.professor.matricula = :professor AND t.disciplina.id = :disciplina");
        query.setParameter("professor", matricula_professor);
        query.setParameter("disciplina", disciplina_id);
        List<Tema> temas = query.list();
        return temas;
    }

    public boolean saveTema(Tema t) throws ITQException {
        try {
            session = sessionFactory.getCurrentSession();
            int tema_id = (int) session.save(t);
            return tema_id != 0;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public List<Tema> listTemaByQuestao(int id) throws ITQException {
        try {
            session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("FROM Tema t JOIN FETCH t.questoes q WHERE q.id = :id");
            query.setParameter("id", id);
            List<Tema> temas = query.list();
            return temas;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
}
