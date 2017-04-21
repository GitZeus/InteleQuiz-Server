package persistencia;

import entidade.Tema;
import util.ITQException;
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

    public boolean saveTema(Tema t) throws ITQException {
        try {
            session = sessionFactory.getCurrentSession();
            Integer tema_id = (Integer) session.save(t);
            return tema_id != null;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
}
