package persistencia;

import entidade.Aluno;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class GatewayAluno {

    @Autowired
    private SessionFactory sessionFactory;
    private Session session;

    public List<Aluno> listAlunoByTurma(int id) {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Aluno a JOIN FETCH a.turmas t WHERE t.id = :id");
        query.setParameter("id", id);
        List<Aluno> alunos = query.list();
        return alunos;
    }

}
