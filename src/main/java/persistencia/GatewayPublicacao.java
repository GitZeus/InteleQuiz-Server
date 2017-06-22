package persistencia;

import entidade.Publicacao;
import enums.StatusPublicacao;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class GatewayPublicacao {

    @Autowired(required = false)
    private SessionFactory sessionFactory;
    private Session session;

    public int savePublicacao(Publicacao publicacao) {
        session = sessionFactory.getCurrentSession();
        int id = (int) session.save(publicacao);
        return id;
    }

    public List<Publicacao> listPublicacaoByStatusByTurma(int id, StatusPublicacao status) {
        List<Publicacao> publicados;
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Publicacao p where p.turma.id = :id AND p.status = :status ORDER BY p.tsEncerramento ASC");
        query.setParameter("id", id);
        query.setParameter("status", status);
        publicados = query.list();
        return publicados;
    }
    
    public List<Publicacao> listPublicacaoByTurma(int id) {
        List<Publicacao> publicados;
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Publicacao p where p.turma.id = :id ORDER BY p.tsEncerramento ASC");
        query.setParameter("id", id);
        publicados = query.list();
        return publicados;
    }

    public Publicacao getPublicacaoById(int id) {
        session = sessionFactory.getCurrentSession();
        Publicacao publicacao = session.get(Publicacao.class, id);
        return publicacao;
    }
}