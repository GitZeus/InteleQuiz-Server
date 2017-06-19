package persistencia;

import entidade.Gabarito;
import entidade.Questao;
import entidade.Quiz;
import entidade.Resposta;
import java.util.ArrayList;
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

    public int saveQuestao(Questao questao) {
        session = sessionFactory.getCurrentSession();
        int id = (int) session.save(questao);
        for (Resposta resposta : questao.getRespostas()) {
            resposta.setQuestao(questao);
            session.save(resposta);
        }
        return id;
    }

    public boolean updateQuestao(Questao questao) {
        session = sessionFactory.getCurrentSession();
        session.update(questao);
        for (Resposta r : questao.getRespostas()) {
            r.setQuestao(questao);
            session.saveOrUpdate(r);
        }
        return true;
    }

    public List<Questao> listQuestaoByQuiz(int id) {
        session = sessionFactory.getCurrentSession();
        Quiz quiz = session.get(Quiz.class, id);
        quiz.getQuestoes().size();
        return quiz.getQuestoes();
    }

    public List<Questao> listQuestaoByTema(int id) {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Questao q LEFT JOIN FETCH q.temas t WHERE t.id =:id");
        query.setParameter("id", id);
        List<Questao> questoes = query.list();
        return questoes;
    }

    public Questao getQuestaoByResposta(Resposta r) {
        session = sessionFactory.getCurrentSession();
        Resposta resposta = session.get(Resposta.class, r.getId());
        return resposta.getQuestao();
    }
    
    public Resposta getRespostaById(int id) {
        session = sessionFactory.getCurrentSession();
        Resposta resposta = session.get(Resposta.class, id);
        return resposta;
    }
    
    public Questao getQuestaoById(int id) {
        session = sessionFactory.getCurrentSession();
        Questao questao = session.get(Questao.class, id);
        return questao;
    }    
}
