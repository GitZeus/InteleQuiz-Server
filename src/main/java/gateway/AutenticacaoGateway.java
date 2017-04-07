package gateway;

import java.util.ArrayList;
import model.application.ITQException;
import java.util.List;
import model.entity.Aluno;
import model.entity.Disciplina;
import model.entity.Professor;
import model.entity.TipoUsuario;
import model.entity.Turma;
import model.entity.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//@Transactional
@Repository
public class AutenticacaoGateway {

    @Autowired
    private SessionFactory sessionFactory;
    private Session session;
    static final List<Professor> PROFESSORES = new ArrayList<Professor>();
    static final List<Aluno> ALUNOS = new ArrayList<Aluno>();

    static {
        Disciplina d1 = new Disciplina(1, "Lógica de Programação", "LP");
        Disciplina d2 = new Disciplina(2, "Análise de Requisitos", "AR");

        Professor prof1 = new Professor(1, "Roberto", "123456", "123456", TipoUsuario.PROFESSOR);

        Aluno a = new Aluno(1, "Elizeu", "123456", "123456", TipoUsuario.ALUNO);

        Turma t1 = new Turma(prof1, d1);
        Turma t2 = new Turma(prof1, d2);
        
        prof1.getDisciplinas().add(d1);
        prof1.getDisciplinas().add(d2);
        
        t1.getAlunos().add(a);
        t2.getAlunos().add(a);

        PROFESSORES.add(prof1);
        ALUNOS.add(a);
    }

    public Usuario getProfessorByLoginSenha(Usuario u) throws ITQException {
        for (Professor p : PROFESSORES) {
            if (u.getLogin().equals(p.getLogin()) && u.getSenha().equals(p.getSenha())) {
                return p;
            }
        }
        throw new ITQException("Login ou senha incorretos");
    }

    public Usuario getAlunoByLoginSenha(Usuario u) throws ITQException {
        for (Aluno a : ALUNOS) {
            if (u.getLogin().equals(a.getLogin()) && u.getSenha().equals(a.getSenha())) {
                return a;
            }
        }
        throw new ITQException("Login ou senha incorretos");
    }

    public Usuario getUsuarioById(Usuario u) {
        session = sessionFactory.getCurrentSession();
        return session.get(Usuario.class, Integer.parseInt(u.getLogin()));
    }

    public Usuario getUsuarioByLoginSenha(Usuario u) throws ITQException {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT u FROM usuario u WHERE u.login = :login AND u.senha = :senha");
        query.setParameter("login", u.getLogin());
        query.setParameter("senha", u.getSenha());
        u = (Usuario) query.uniqueResult();

        if (u != null) {
            return u;
        } else {
            throw new ITQException("Login ou Senha incorretos");
        }

    }

    public List<Usuario> listUsuario() {
        session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Usuario").list();
    }

    public void saveUsuario(Usuario u) {
        session = sessionFactory.getCurrentSession();
        session.save(u);
    }

    public void updateUsuario(Usuario u) {
        session = sessionFactory.getCurrentSession();
        session.update(u);
    }

    public void deleteUsuario(Usuario u) {
        session = sessionFactory.getCurrentSession();
        session.delete(u);
    }
}
