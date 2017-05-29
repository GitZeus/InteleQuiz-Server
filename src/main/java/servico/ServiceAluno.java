package servico;

import entidade.Aluno;
import entidade.Turma;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayAluno;
import persistencia.GatewayTurma;

@Service
public class ServiceAluno {

    @Autowired
    GatewayAluno gatewayAluno;

    public List<Aluno> listAlunoByTurma(int id) {
        List<Aluno> alunos = gatewayAluno.listAlunoByTurma(id);
        return alunos;
    }
}