package persistencia;

import entidade.Quiz;
import entidade.Treino;
import java.util.List;
import org.hibernate.Query;
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

    public int saveQuiz(Quiz quiz) {
        session = sessionFactory.getCurrentSession();
        int id = (int) session.save(quiz);
        return id;
    }

    public boolean updateQuiz(Quiz quiz) {
        session = sessionFactory.getCurrentSession();
        session.update(quiz);
        return true;
    }

    public List<Quiz> listQuizByDisciplinaByProfessor(String matricula, int id) {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Quiz q WHERE q.professor.matricula = :matricula AND q.disciplina.id = :id");
        query.setParameter("matricula", matricula);
        query.setParameter("id", id);
        List<Quiz> quizzes = query.list();
        return quizzes;
    }

    public Quiz getQuizByTreino(Treino treino) {
        session = sessionFactory.getCurrentSession();
        Quiz quiz = session.get(Quiz.class, treino.getPublicacao().getQuiz().getId());
        quiz.getQuestoes().size();
        return quiz;
    }
    
    public Quiz getQuizById(int id) {
        session = sessionFactory.getCurrentSession();
        Quiz quiz = session.get(Quiz.class, id);
        quiz.getQuestoes().size();
        return quiz;
    }
}
