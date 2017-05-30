package persistencia;

import entidade.Disciplina;
import entidade.Professor;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class GatewayDisciplina {

    @Autowired(required = false)
    private SessionFactory sessionFactory;
    private Session session;

    public List<Disciplina> listDisciplinaByProfessor(String matricula) {
        session = sessionFactory.getCurrentSession();
        Professor professor = session.get(Professor.class, matricula);
        List<Disciplina> disciplinas = professor.getDisciplinas();
        return disciplinas;
    }

}
