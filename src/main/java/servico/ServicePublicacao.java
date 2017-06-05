package servico;

import entidade.Publicacao;
import enums.StatusTurmaQuiz;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayPublicacao;
import util.ITQException;
import util.RestMessage;
import util.RestMessageType;

@Service
public class ServicePublicacao {

    @Autowired
    private GatewayPublicacao gatewayPublicacao;

    public RestMessage savePublicacao(Publicacao publicacao) throws ITQException {
        validarPublicacao(publicacao);
        try {
            RestMessage message = new RestMessage();
            List<Publicacao> publicados = listPublicacaoByStatusByTurma(publicacao.getTurma().getId(), StatusTurmaQuiz.PUBLICADO);

            if (publicados.size() > 0) {
                message.setText("Esta turma já possui um quiz em andamento");
                message.setType(RestMessageType.WARNING);
            } else {
                publicacao.setStatus(StatusTurmaQuiz.PUBLICADO);
                int id = gatewayPublicacao.savePublicacao(publicacao);
                if (id != 0) {
                    message.setText("Quiz publicado com sucesso");
                    message.setType(RestMessageType.SUCCESS);
                } else {
                    message.setText("Erro ao publicar Quiz, contate o administrador do sistema");
                    message.setType(RestMessageType.ERROR);
                }
            }

            return message;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException(e.getMessage());
        }
    }

    private void validarPublicacao(Publicacao publicacao) throws ITQException {
        if (publicacao.getQuiz() == null) {
            throw new ITQException("Erro ao recuperar o quiz para a publicação");
        }
        if (publicacao.getTsEncerramento() == null) {
            throw new ITQException("Informe uma data de encerramento para a publicação");
        }
        if (publicacao.getTurma() == null) {
            throw new ITQException("Erro ao recuperar a turma para a publicação");
        }
    }

    public List<Publicacao> listPublicacaoByStatusByTurma(int id, StatusTurmaQuiz status) throws ITQException {
        if (id <= 0) {
            throw new ITQException("Informe um id válido para a turma");
        }
        if (status == null) {
            throw new ITQException("Informe um status válido para a publicação");
        }
        try {
            List<Publicacao> publicacoes = gatewayPublicacao.listPublicacaoByStatusByTurma(id, status);
            return publicacoes;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao listar publicação por status e turma");
        }
    }

    public Publicacao getPublicacaoById(int id) throws ITQException {
        if (id <= 0) {
            throw new ITQException("Informe um id válido para a publicação");
        }
        try {
            Publicacao publicacao = gatewayPublicacao.getPublicacaoById(id);
            return publicacao;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao recuperar publicação por id");
        }
    }
}
