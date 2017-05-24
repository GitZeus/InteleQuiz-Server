package entidade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Desempenho {

    private Turma turma;
    private List<Double> aproveitamentos = new ArrayList<>();
    private List<Double> envolvimentos = new ArrayList<>();
    private List<String> encerramentos = new ArrayList<>();
    private Double medAproveitamento;
    private Double medEnvolvimento;
    private Tema temaAtencao;

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Double getMedAproveitamento() {
        return medAproveitamento;
    }

    public void setMedAproveitamento(Double medAproveitamento) {
        this.medAproveitamento = medAproveitamento;
    }

    public Double getMedEnvolvimento() {
        return medEnvolvimento;
    }

    public void setMedEnvolvimento(Double medEnvolvimento) {
        this.medEnvolvimento = medEnvolvimento;
    }

    public Tema getTemaAtencao() {
        return temaAtencao;
    }

    public void setTemaAtencao(Tema temaAtencao) {
        this.temaAtencao = temaAtencao;
    }

    public List<Double> getAproveitamentos() {
        return aproveitamentos;
    }

    public void setAproveitamentos(List<Double> aproveitamentos) {
        this.aproveitamentos = aproveitamentos;
    }

    public List<String> getEncerramentos() {
        return encerramentos;
    }

    public void setEncerramentos(List<String> encerramentos) {
        this.encerramentos = encerramentos;
    }

    public List<Double> getEnvolvimentos() {
        return envolvimentos;
    }

    public void setEnvolvimentos(List<Double> envolvimentos) {
        this.envolvimentos = envolvimentos;
    }

}
