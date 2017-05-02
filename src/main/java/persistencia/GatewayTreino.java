package persistencia;

import entidade.Tema;
import entidade.Turma;
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
public class GatewayTreino {

    @Autowired(required = false)
    private SessionFactory sessionFactory;
    private Session session;

    public List<Turma> listTurmasByAluno(String ra) throws ITQException {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Turma t JOIN FETCH t.alunos a WHERE a.ra = :ra");
        query.setParameter("ra", ra);
        List<Turma> turmas = query.list();
        return turmas;
    }

    public boolean saveTema(Tema t) throws ITQException {
        try {
            session = sessionFactory.getCurrentSession();
            Integer tema_id = (Integer) session.save(t);
            return tema_id != null;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
}
