package persistencia;

import entidade.Tema;
import entidade.Turma;
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
public class GatewayTurma {

    @Autowired
    private SessionFactory sessionFactory;
    private Session session;

    public Turma getTurmaById(int id) {
        session = sessionFactory.getCurrentSession();
        Turma turma = session.get(Turma.class, id);
        return turma;
    }

}
