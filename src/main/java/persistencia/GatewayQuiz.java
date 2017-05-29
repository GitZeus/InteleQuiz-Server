package persistencia;

import entidade.Questao;
import entidade.Quiz;
import entidade.Turma;
import entidade.Publicacao;
import enums.StatusTurmaQuiz;
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

    public List<Turma> listTurmasByProfessorByDisciplina(String matricula, int id) throws ITQException {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Turma t WHERE t.professor.matricula = :matricula AND t.disciplina.id = :id");
        query.setParameter("matricula", matricula);
        query.setParameter("id", id);
        List<Turma> turmas = query.list();
        return turmas;
    }

    public List<Quiz> listQuizByDisciplinaByProfessor(String matricula_professor, int disciplina_id) throws ITQException {
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
            int quiz_id = (int) session.save(q);
            return quiz_id != 0;
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

    public List<Questao> listQuestoesByQuiz(int quiz_id) throws ITQException {
        session = sessionFactory.getCurrentSession();
        Quiz quiz = session.get(Quiz.class, quiz_id);
        quiz.getQuestoes().size();
        return quiz.getQuestoes();
    }

    public boolean publicarQuiz(Publicacao tq) throws ITQException {
        try {
            session = sessionFactory.getCurrentSession();
            int turmaQuiz_id = (int) session.save(tq);
            return turmaQuiz_id != 0;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public List<Publicacao> listQuizPublicadoByStatusByTurma(int id, StatusTurmaQuiz status) throws ITQException {
        try {
            List<Publicacao> publicados;
            session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("FROM Publicacao p where p.turma.id = :id AND p.status = :status ORDER BY p.tsEncerramento ASC");
            query.setParameter("id", id);
            query.setParameter("status", status);
            publicados = query.list();
            return publicados;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public List<Publicacao> listQuizEmAndamentoByTurma(int id) throws ITQException {
        try {
            List<Publicacao> publicados;
            session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("FROM Publicacao p where p.turma.id = :id AND p.status = :status");
            query.setParameter("id", id);
            query.setParameter("status", StatusTurmaQuiz.PUBLICADO);
            publicados = query.list();
            return publicados;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public Publicacao getQuizPublicadoById(int id) {
        session = sessionFactory.getCurrentSession();
        Publicacao publicacao = session.get(Publicacao.class, id);
        return publicacao;
    }
}
