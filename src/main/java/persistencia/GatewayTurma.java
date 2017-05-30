package persistencia;

import entidade.Turma;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class GatewayTurma {

    @Autowired
    private SessionFactory sessionFactory;
    private Session session;

    public Turma getTurmaById(int id) {
        session = sessionFactory.getCurrentSession();
        Turma turma = session.get(Turma.class, id);
        return turma;
    }

    public List<Turma> listTurmaByProfessor(String matricula) {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Turma t WHERE t.professor.matricula = :matricula");
        query.setParameter("matricula", matricula);
        List<Turma> turmas = query.list();
        return turmas;
    }

    public List<Turma> listTurmaByProfessorByDisciplina(String matricula, int id) {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Turma t WHERE t.professor.matricula = :matricula AND t.disciplina.id = :id");
        query.setParameter("matricula", matricula);
        query.setParameter("id", id);
        List<Turma> turmas = query.list();
        return turmas;
    }

    public List<Turma> listTurmaByAluno(String ra) {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Turma t JOIN FETCH t.alunos a WHERE a.ra = :ra");
        query.setParameter("ra", ra);
        List<Turma> turmas = query.list();
        return turmas;
    }
}