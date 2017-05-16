package persistencia;

import entidade.Aluno;
import entidade.Questao;
import entidade.Quiz;
import entidade.Resposta;
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
public class GatewayRanking {

    @Autowired(required = false)
    private SessionFactory sessionFactory;
    private Session session;

//    public List<Turma> listRankingTurmasByAluno(String ra) throws ITQException {
//        session = sessionFactory.getCurrentSession();
//        Query query = session.createQuery("FROM Turma t JOIN FETCH t.alunos a WHERE a.ra = :ra");
//        query.setParameter("ra", ra);
//        List<Turma> turmas = query.list();
//        return turmas;
//    }
    public List<TurmaQuiz> listQuizPublicadoByTurma(Integer turma_id) throws ITQException {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM TurmaQuiz tq WHERE tq.turma.id = :turma_id");
        query.setParameter("turma_id", turma_id);
        List<TurmaQuiz> publicacoes = query.list();
        return publicacoes;
    }

    public List<Treino> listTreinoByAlunoByTurma(String ra, Integer id) throws ITQException {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Treino t WHERE t.aluno.ra = :ra AND t.turmaQuiz.turma.id = :id");
        query.setParameter("ra", ra);
        query.setParameter("id", id);
        List<Treino> treinos = query.list();
        return treinos;
    }

    public List<Aluno> listAlunosByTurma(Integer id) throws ITQException {
        session = sessionFactory.getCurrentSession();
        Turma turma = session.get(Turma.class, id);
        turma.getAlunos().size();
        return turma.getAlunos();
    }
}
