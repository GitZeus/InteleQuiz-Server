package servico;

import entidade.Turma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayTurma;

@Service
public class ServiceTurma {

    @Autowired
    GatewayTurma gatewayTurma;

    public Turma getTurmaById(Integer id) {
        Turma turma = gatewayTurma.getTurmaById(id);
        return turma;
    }
}