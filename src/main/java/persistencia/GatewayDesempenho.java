package persistencia;

import entidade.Tema;
import entidade.TurmaQuiz;
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
public class GatewayDesempenho {

    @Autowired(required = false)
    private SessionFactory sessionFactory;
    private Session session;
    
    public List<TurmaQuiz> listQuizEncerradoByTurma(Integer id, StatusTurmaQuiz status) throws ITQException {
        try {
            List<TurmaQuiz> publicados;
            session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("FROM TurmaQuiz tq where tq.turma.id = :id AND tq.status = :status ORDER BY tq.tsEncerramento DESC");
            query.setParameter("id", id);
            query.setParameter("status", status);
            publicados = query.list();
            return publicados;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
}
