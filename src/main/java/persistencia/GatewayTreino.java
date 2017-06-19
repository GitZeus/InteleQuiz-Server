package persistencia;

import entidade.Gabarito;
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
            t.getGabaritos().size();
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
            t.getGabaritos().size();
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
            t.getGabaritos().size();
        }
        return treinos;
    }

    public List<Gabarito> listGabaritoByTreino(int id) {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Gabarito g WHERE g.treino_id = :id");
        query.setParameter("id", id);
        List<Gabarito> gabaritos = query.list();
        return gabaritos;
    }

    public Gabarito getNextGabarito(Gabarito gabarito) {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Gabarito g WHERE g.treino_id = :treino_id AND g.ts_resposta IS NULL");
        query.setParameter("treino_id", gabarito.getTreino_id());
        query.setMaxResults(1);
        List<Gabarito> gabaritos = query.list();
        if(gabaritos.size() > 0){
            return gabaritos.get(0);
        }else{
            return null;
        }
    }

    public Gabarito getGabaritoByID(Gabarito g) {
        Gabarito.GabaritoPK pk = new Gabarito.GabaritoPK(g.getQuestao_id(), g.getTreino_id());
        session = sessionFactory.getCurrentSession();
        Gabarito gabarito = session.get(Gabarito.class, pk);
        return gabarito;
    }
    
    public long getQtdRespostasTreino(Treino treino){
        long qtd = 0;
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT COUNT(g) FROM Gabarito g WHERE g.treino_id = :treino_id AND g.visualizada = :visualizada");
        query.setParameter("treino_id", treino.getId());
        query.setParameter("visualizada", true);
        qtd = (long) query.uniqueResult();
        return qtd;
    }
}
