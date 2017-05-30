package persistencia;

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
public class GatewayTreino {

    @Autowired(required = false)
    private SessionFactory sessionFactory;
    private Session session;

    public int saveTreino(Treino treino) {
        session = sessionFactory.getCurrentSession();
        int id = (int) session.save(treino);
        return id;
    }

    public Treino updateTreino(Treino treino) {
        session = sessionFactory.getCurrentSession();
        session.update(treino);
        return treino;
    }

    public Treino getTreinoById(int id) {
        session = sessionFactory.getCurrentSession();
        Treino treino = session.get(Treino.class, id);
        return treino;
    }

    public List<Treino> listTreinoByPublicacao(int id) {
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
