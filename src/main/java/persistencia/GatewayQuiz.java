package persistencia;

import entidade.Quiz;
import entidade.Turma;
import java.util.List;
import org.hibernate.Query;
import util.ITQException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class GatewayQuiz {

    @Autowired(required = false)
    private SessionFactory sessionFactory;
    private Session session;

    @Transactional
    public List<Turma> listTurmasByProfessor(String matricula) throws ITQException {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Turma t WHERE t.professor.matricula = :matricula");
        query.setParameter("matricula", matricula);
        List<Turma> turmas = query.list();
        return turmas;
    }
    
    public List<Quiz> listQuizByDisciplinaByProfessor(String matricula_professor, Integer disciplina_id) throws ITQException {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Quiz q WHERE q.professor.matricula = :professor AND q.disciplina.id = :disciplina");
        query.setParameter("professor", matricula_professor);
        query.setParameter("disciplina", disciplina_id);
        List<Quiz> quizzes = query.list();
        return quizzes;
    }
}
