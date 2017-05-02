package persistencia;

import entidade.Aluno;
import entidade.Professor;
import entidade.Usuario;
import enums.TipoUsuario;
import util.ITQException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class GatewayAutenticacao {

    @Autowired
    private SessionFactory sessionFactory;
    private Session session;

    public Professor getProfessorByLoginSenha(Usuario u) throws ITQException {
        try {
            Professor pMock = new Professor();
            pMock.setLogin("MA123");
            pMock.setSenha("123");
            pMock.setNome("Roberto Cantanhede");
            pMock.setMatricula(pMock.getLogin());
            pMock.setPerfil(TipoUsuario.PROFESSOR);
            if (u.getLogin().equals(pMock.getLogin()) && u.getSenha().equals(pMock.getSenha())) {
                return pMock;
            } else {
                throw new ITQException("Login ou senha incorretos");
            }
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public Aluno getAlunoByLoginSenha(Usuario u) throws ITQException {
        try {
            Aluno aMock = new Aluno();
            aMock.setLogin("21550465");
            aMock.setSenha("123");
            aMock.setNome("Elizeu Freitas");
            aMock.setRa(aMock.getLogin());
            aMock.setPerfil(TipoUsuario.ALUNO);
            if (u.getLogin().equals(aMock.getLogin()) && u.getSenha().equals(aMock.getSenha())) {
                return aMock;
            } else {
                throw new ITQException("Login ou senha incorretos");
            }
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
}
