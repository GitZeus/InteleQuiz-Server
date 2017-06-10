package persistencia;

import entidade.Gabarito;
import entidade.Gabarito.GabaritoPK;
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

    public GabaritoPK saveGabarito(Gabarito gabarito) {
        session = sessionFactory.getCurrentSession();
        GabaritoPK pk = (GabaritoPK) session.save(gabarito);
        return pk;
    }
    
    public void updateGabarito(Gabarito gabarito) {
        session = sessionFactory.getCurrentSession();
        session.update(gabarito);
    }
    
    public Gabarito getGabaritoById(GabaritoPK id) {
        session = sessionFactory.getCurrentSession();
        Gabarito gabarito = (Gabarito) session.get(Gabarito.class, id);
        return gabarito;
    }
}
