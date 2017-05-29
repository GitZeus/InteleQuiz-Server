package persistencia;

import entidade.Aluno;
import entidade.Questao;
import entidade.Quiz;
import entidade.Resposta;
import entidade.Tema;
import entidade.Treino;
import entidade.Turma;
import entidade.Publicacao;
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

    public Treino startNewTreino(String ra, int turma_quiz_id) throws ITQException {
        try {
            session = sessionFactory.getCurrentSession();

            Aluno a = session.get(Aluno.class, ra);
            Publicacao tq = session.get(Publicacao.class, turma_quiz_id);
            Treino t = new Treino();
            t.setAluno(a);
            t.setPublicacao(tq);

            session.save(t);
            return t;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public Treino updateTreino(Treino treino) throws ITQException {
        try {
            session = sessionFactory.getCurrentSession();
            session.update(treino);
            return treino;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public Questao getQuestaoByResposta(Resposta r) throws ITQException {
        try {
            session = sessionFactory.getCurrentSession();
            Resposta resposta = session.get(Resposta.class, r.getId());
            return resposta.getQuestao();
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public Quiz getQuizByTreino(Treino treino) throws ITQException {
        try {
            session = sessionFactory.getCurrentSession();
            Quiz quiz = session.get(Quiz.class, treino.getPublicacao().getQuiz().getId());
            quiz.getQuestoes().size();
            return quiz;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public List<Treino> listTreinoByPublicacao(int id) throws ITQException {
        try {
            session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("FROM Treino t WHERE t.publicacao.id = :id");
            query.setParameter("id", id);
            List<Treino> treinos = query.list();
            for(Treino t: treinos){
                t.getRespostas().size();
            }
            return treinos;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public Treino getTreino(int id) throws ITQException {
        session = sessionFactory.getCurrentSession();
        Treino treino = session.get(Treino.class, id);
        return treino;
    }

    public List<Treino> listTreinoByQuizPublicado(int id) {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Treino t WHERE t.publicacao.id = :id");
        query.setParameter("id", id);
        List<Treino> treinos = query.list();
        for (Treino t : treinos) {
            t.getRespostas().size();
        }
        return treinos;
    }

    public List<Treino> listTreinoByTurmaByAluno(int id, String ra) {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Treino t WHERE t.publicacao.turma.id = :id AND t.aluno.ra = :ra");
        query.setParameter("id", id);
        query.setParameter("ra", ra);
        List<Treino> treinos = query.list();
        for (Treino t : treinos) {
            t.getRespostas().size();
        }
        return treinos;
    }
    
    public List<Treino> listTreinoByPublicacaoByAluno(int id, String ra) {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Treino t WHERE t.publicacao.id = :id AND t.aluno.ra = :ra");
        query.setParameter("id", id);
        query.setParameter("ra", ra);
        List<Treino> treinos = query.list();
        for (Treino t : treinos) {
            t.getRespostas().size();
        }
        return treinos;
    }
}
