package persistencia;

import entidade.Questao;
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

@Transactional
@Repository
public class GatewayQuiz {

    @Autowired(required = false)
    private SessionFactory sessionFactory;
    private Session session;

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

    public boolean saveQuiz(Quiz q) throws ITQException {
        try {
            session = sessionFactory.getCurrentSession();
            Integer quiz_id = (Integer) session.save(q);
            return quiz_id != null;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public boolean updateQuiz(Quiz q) throws ITQException {
        try {
            session = sessionFactory.getCurrentSession();
            session.update(q);
            return true;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public List<Questao> listQuestoesByQuiz(Integer quiz_id) throws ITQException {
        session = sessionFactory.getCurrentSession();
        Quiz quiz = session.get(Quiz.class, quiz_id);
        quiz.getQuestoes().size();
        return quiz.getQuestoes();
    }
}