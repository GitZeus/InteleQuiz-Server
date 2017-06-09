package persistencia;

import entidade.Gabarito;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class GatewayGabarito {

    @Autowired(required = false)
    private SessionFactory sessionFactory;
    private Session session;

    public int saveGabarito(Gabarito gabarito) {
        session = sessionFactory.getCurrentSession();
        int id = (int) session.save(gabarito);
        return id;
    }
    
    public Gabarito getGabaritoById(int id) {
        session = sessionFactory.getCurrentSession();
        Gabarito gabarito = (Gabarito) session.get(Gabarito.class, id);
        return gabarito;
    }
}
