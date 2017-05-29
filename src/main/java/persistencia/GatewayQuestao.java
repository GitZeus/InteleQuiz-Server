package persistencia;

import entidade.Disciplina;
import entidade.Professor;
import entidade.Questao;
import entidade.Resposta;
import entidade.Tema;
import util.ITQException;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class GatewayQuestao {

    @Autowired(required = false)
    private SessionFactory sessionFactory;
    private Session session;

    public List<Disciplina> listDisciplinasByProfessor(Professor p) throws ITQException {
        try {
            session = sessionFactory.getCurrentSession();
            Professor professor = session.get(Professor.class, p.getMatricula());
            List<Disciplina> disciplinas = professor.getDisciplinas();
            return disciplinas;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ITQException(e.getMessage());
        }
    }

    public List<Questao> listQuestoesByTema(int tema_id) throws ITQException {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Questao q LEFT JOIN FETCH q.temas t WHERE t.id =:tema_id");
        query.setParameter("tema_id", tema_id);
        List<Questao> questoes = query.list();
        return questoes;
    }

    public List<Tema> listTemasByQuestao(int questao_id) throws ITQException {
        session = sessionFactory.getCurrentSession();
        Questao q = session.get(Questao.class, questao_id);
        q.getTemas().size();
        List<Tema> temas = q.getTemas();
        return temas;
    }

    public boolean saveQuestao(Questao questao) throws ITQException {
        try {
            session = sessionFactory.getCurrentSession();
            int questao_id = (int) session.save(questao);
            for (Resposta resposta : questao.getRespostas()) {
                resposta.setQuestao(questao);
                session.save(resposta);
            }
            return questao_id != 0;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public boolean updateQuestao(Questao questao) throws ITQException {
        try {
            session = sessionFactory.getCurrentSession();
            session.update(questao);
            for (Resposta r : questao.getRespostas()) {
                r.setQuestao(questao);
                session.saveOrUpdate(r);
            }
            return true;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
}
