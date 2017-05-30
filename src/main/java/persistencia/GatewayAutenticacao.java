package persistencia;

import entidade.Aluno;
import entidade.Professor;
import entidade.Usuario;
import enums.TipoUsuario;
import java.util.ArrayList;
import java.util.List;
import util.ITQException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class GatewayAutenticacao {

    public Professor getProfessorByLoginSenha(Usuario u) throws ITQException {
        try {
            Professor pMock = new Professor();
            pMock.setLogin("MA123");
            pMock.setSenha("123");
            pMock.setNome("Roberto Cantanhede");
            pMock.setMatricula(pMock.getLogin());
            pMock.setPerfil(TipoUsuario.PROFESSOR);
            if (u.getLogin().equals(pMock.getLogin()) && u.getSenha().equals(pMock.getSenha())) {
                pMock.setSenha(null);
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

            Aluno bMock = new Aluno();
            bMock.setLogin("21550466");
            bMock.setSenha("123");
            bMock.setNome("Thayllan Siqueira");
            bMock.setRa(bMock.getLogin());
            bMock.setPerfil(TipoUsuario.ALUNO);

            Aluno cMock = new Aluno();
            cMock.setLogin("21550467");
            cMock.setSenha("123");
            cMock.setNome("Leandro David");
            cMock.setRa(cMock.getLogin());
            cMock.setPerfil(TipoUsuario.ALUNO);

            List<Aluno> alunos = new ArrayList<>();
            alunos.add(aMock);
            alunos.add(bMock);
            alunos.add(cMock);

            for (int i = 0; i < alunos.size(); i++) {
                if (u.getLogin().equals(alunos.get(i).getLogin()) && u.getSenha().equals(alunos.get(i).getSenha())) {
                    alunos.get(i).setSenha(null);
                    return alunos.get(i);
                }
            }
            throw new ITQException("Login ou senha incorretos");
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
}
