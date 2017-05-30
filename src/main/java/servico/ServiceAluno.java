package servico;

import entidade.Aluno;
import entidade.Turma;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayAluno;
import persistencia.GatewayTurma;
import util.ITQException;

@Service
public class ServiceAluno {

    @Autowired
    GatewayAluno gatewayAluno;

    public List<Aluno> listAlunoByTurma(int id) {
        List<Aluno> alunos = gatewayAluno.listAlunoByTurma(id);
        return alunos;
    }

    public Aluno getAlunoByRa(String ra) throws ITQException {
        if (ra == null || ra.length() == 0) {
            throw new ITQException("Informe um registro acadêmico válido para o aluno");
        }
        try {
            Aluno aluno = gatewayAluno.getAlunoByRa(ra);
            return aluno;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao recuperar aluno por registro acadêmico");
        }
    }
}
