package persistencia;

import entidade.Tema;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class GatewayTema {

    @Autowired(required = false)
    private SessionFactory sessionFactory;
    private Session session;

    public int saveTema(Tema tema) {
        session = sessionFactory.getCurrentSession();
        int id = (int) session.save(tema);
        return id;
    }

    public Tema getTemaById(int id) {
        session = sessionFactory.getCurrentSession();
        Tema tema = session.get(Tema.class, id);
        return tema;
    }

    public List<Tema> listTemasByDisciplinaByProfessor(String matricula, int id) {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Tema t WHERE t.professor.matricula = :matricula AND t.disciplina.id = :id");
        query.setParameter("matricula", matricula);
        query.setParameter("id", id);
        List<Tema> temas = query.list();
        return temas;
    }

    public List<Tema> listTemaByQuestao(int id) {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Tema t JOIN FETCH t.questoes q WHERE q.id = :id");
        query.setParameter("id", id);
        List<Tema> temas = query.list();
        return temas;
    }
}