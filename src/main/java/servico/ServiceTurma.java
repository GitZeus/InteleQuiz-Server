package servico;

import entidade.Turma;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayTurma;
import util.ITQException;

@Service
public class ServiceTurma {

    @Autowired
    GatewayTurma gatewayTurma;

    public Turma getTurmaById(int id) throws ITQException {
        if (id <= 0) {
            throw new ITQException("Informe um id válido para a turma");
        }
        try {
            Turma turma = gatewayTurma.getTurmaById(id);
            return turma;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao recuperar turma por id");
        }
    }

    public List<Turma> listTurmaByProfessor(String matricula) throws ITQException {
        if (matricula == null || matricula.length() == 0) {
            throw new ITQException("Erro ao recuperar a matricula do professor");
        }
        try {
            List<Turma> turmas = gatewayTurma.listTurmaByProfessor(matricula);
            return turmas;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao listar turmas por professor");
        }
    }

    public List<Turma> listTurmaByProfessorByDisciplina(String matricula, int id) throws ITQException {
        if (matricula == null || matricula.length() == 0) {
            throw new ITQException("Erro ao recuperar a matricula do professor");
        }
        if (id <= 0) {
            throw new ITQException("Informe um id válido para a disciplina");
        }
        try {
            List<Turma> turmas = gatewayTurma.listTurmaByProfessorByDisciplina(matricula, id);
            return turmas;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao listar turmas por professor e disciplina");
        }
    }

    public List<Turma> listTurmaByAluno(String ra) throws ITQException {
        if (ra == null || ra.length() == 0) {
            throw new ITQException("Erro ao recuperar o registro acadêmico do aluno");
        }
        try {
            List<Turma> turmas = gatewayTurma.listTurmaByAluno(ra);
            return turmas;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao listar turmas por aluno");
        }
    }
}
