package servico;

import entidade.Aluno;
import entidade.Treino;
import entidade.Turma;
import entidade.Publicacao;
import enums.StatusTurmaQuiz;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.ITQException;
import util.RankingVO;

@Service
public class ServiceRanking {

    @Autowired
    private ServiceTurma serviceTurma;

    @Autowired
    private ServiceTreino serviceTreino;

    @Autowired
    private ServiceAluno serviceAluno;

    @Autowired
    private ServicePublicacao servicePublicacao;

    public List<RankingVO> getRankingTurmaByAluno(String ra) throws ITQException {
        try {
            List<Turma> turmas = serviceTurma.listTurmaByAluno(ra);
            List<RankingVO> ranking = calculaRankingTurma(turmas);
            return ranking;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public List<RankingVO> getRankingTurmaByProfessor(String matricula) throws ITQException {
        try {
            List<Turma> turmas = serviceTurma.listTurmaByProfessor(matricula);
            List<RankingVO> ranking = calculaRankingTurma(turmas);
            return ranking;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
    
    private List<RankingVO> calculaRankingTurma(List<Turma> turmas) throws ITQException {
        List<RankingVO> ranking = new ArrayList<>();
        for (Turma turma : turmas) {
            List<Publicacao> publicacoes = servicePublicacao.listPublicacaoByStatusByTurma(turma.getId(), StatusTurmaQuiz.ENCERRADO);
            RankingVO rvo = new RankingVO();
            int qtdTreinos = 0;
            
            for (Publicacao publicacao : publicacoes) {
                List<Treino> treinos = serviceTreino.listTreinoByPublicacao(publicacao.getId());
                RankingVO rvoTemp = calculaIndicadoresTreino(treinos);
                rvo.setAproveitamento(rvo.getAproveitamento() + rvoTemp.getAproveitamento());
                rvo.setPontuacao(rvo.getPontuacao() + rvoTemp.getPontuacao());
                if(treinos != null){
                    qtdTreinos += treinos.size();
                }
            }

            if (qtdTreinos > 0) {
                rvo.setAproveitamento(rvo.getAproveitamento() / qtdTreinos);
            }

            rvo.setId(turma.getId());
            rvo.setAno(turma.getAno());
            rvo.setLetra(turma.getLetra());
            rvo.setTurno(turma.getTurno());
            rvo.setDisciplina(turma.getDisciplina());
            rvo.setSemestre(turma.getSemestre());

            ranking.add(rvo);
        }
        return ranking;
    }

    public List<RankingVO> getRankingAlunoByTurma(int id) throws ITQException {
        try {
            List<Aluno> alunos = serviceAluno.listAlunoByTurma(id);
            List<RankingVO> ranking = calculaRankingAluno(alunos, id);
            return ranking;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    private List<RankingVO> calculaRankingAluno(List<Aluno> alunos, int id) throws ITQException {
        List<RankingVO> ranking = new ArrayList<>();
        for (Aluno aluno : alunos) {
            RankingVO rvo = new RankingVO();
            List<Treino> treinos = serviceTreino.listTreinoByTurmaByAluno(id, aluno.getRa());

            rvo = calculaIndicadoresTreino(treinos);

            rvo.setAluno(aluno);
            ranking.add(rvo);
        }
        return ranking;
    }

    private RankingVO calculaIndicadoresTreino(List<Treino> treinos) {
        RankingVO rvo = new RankingVO();
        for (Treino treino : treinos) {
            rvo.setPontuacao(rvo.getPontuacao() + treino.getPontuacao());
            rvo.setAproveitamento(rvo.getAproveitamento() + treino.getAproveitamento());
        }
        if (treinos != null && treinos.size() > 0) {
            rvo.setAproveitamento(rvo.getAproveitamento() / treinos.size());
        }
        return rvo;
    }
}
