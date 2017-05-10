package persistencia;

import entidade.Aluno;
import entidade.Tema;
import entidade.Treino;
import entidade.Turma;
import entidade.TurmaQuiz;
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

    public Treino startNewTreino(String ra, Integer turma_quiz_id) throws ITQException {
        try {
            session = sessionFactory.getCurrentSession();
            
            Aluno a = session.get(Aluno.class, ra);
            TurmaQuiz tq = session.get(TurmaQuiz.class, turma_quiz_id);
            Treino t = new Treino();
            t.setAluno(a);
            t.setTurmaQuiz(tq);
            
            session.save(t);
            return t;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
}
