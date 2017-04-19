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

    public List<Tema> listTemasByDisciplinaByProfessor(String matricula_professor, Integer disciplina_id) throws ITQException {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Tema t WHERE t.professor.matricula = :professor AND t.disciplina.id = :disciplina");
        query.setParameter("professor", matricula_professor);
        query.setParameter("disciplina", disciplina_id);
        List<Tema> temas = query.list();
        return temas;
    }

    public List<Questao> listQuestoesByTema(Integer tema_id) throws ITQException {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Questao q LEFT JOIN FETCH q.temas t WHERE t.id =:tema_id");
        query.setParameter("tema_id", tema_id);
        List<Questao> questoes = query.list();
        return questoes;
    }

    public boolean saveQuestao(Questao questao) throws ITQException {
        System.out.println("TEMAS: " + questao.getTemas());
        try {
            session = sessionFactory.getCurrentSession();
            Integer questao_id = (Integer) session.save(questao);
            for(Resposta resposta : questao.getRespostas()){
                resposta.setQuestao(questao);
                session.save(resposta);
            }
            return questao_id != null;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
}
