package model.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
@Service
public class UsuarioDAO {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public Usuario getPersonById(int cod_usuario) {
        return hibernateTemplate.get(Usuario.class, cod_usuario);
    }
}
