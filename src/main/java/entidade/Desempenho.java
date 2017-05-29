package entidade;

import java.util.ArrayList;
import java.util.List;

public class Desempenho {

    private List<Publicacao> publicacoes = new ArrayList<>();
    private List<String> encerramentos = new ArrayList<>();
    private List<Tema> temasCriticos = new ArrayList<>();
    private List<Questao> questoesCriticas = new ArrayList<>();

    private List<Double> aproveitamentosTurma = new ArrayList<>();
    private List<Double> envolvimentosTurma = new ArrayList<>();
    private double medAproveitamentoTurma;
    private double medEnvolvimentoTurma;

    private List<Double> aproveitamentosAluno = new ArrayList<>();
    private List<Double> envolvimentosAluno = new ArrayList<>();
    private double medAproveitamentoAluno;
    private double medEnvolvimentoAluno;

    public void somaMedAproveitamentoTurma(double aprov) {
        medAproveitamentoTurma += aprov;
    }

    public void somaMedAproveitamentoAluno(double aprov) {
        medAproveitamentoAluno += aprov;
    }

    public List<Double> getAproveitamentosTurma() {
        return aproveitamentosTurma;
    }

    public void setAproveitamentosTurma(List<Double> aproveitamentosTurma) {
        this.aproveitamentosTurma = aproveitamentosTurma;
    }

    public List<Double> getEnvolvimentosTurma() {
        return envolvimentosTurma;
    }

    public void setEnvolvimentosTurma(List<Double> envolvimentosTurma) {
        this.envolvimentosTurma = envolvimentosTurma;
    }

    public List<Double> getAproveitamentosAluno() {
        return aproveitamentosAluno;
    }

    public void setAproveitamentosAluno(List<Double> aproveitamentosAluno) {
        this.aproveitamentosAluno = aproveitamentosAluno;
    }

    public List<Double> getEnvolvimentosAluno() {
        return envolvimentosAluno;
    }

    public void setEnvolvimentosAluno(List<Double> envolvimentosAluno) {
        this.envolvimentosAluno = envolvimentosAluno;
    }

    public double getMedAproveitamentoAluno() {
        return medAproveitamentoAluno;
    }

    public void setMedAproveitamentoAluno(double medAproveitamentoAluno) {
        this.medAproveitamentoAluno = medAproveitamentoAluno;
    }

    public double getMedEnvolvimentoAluno() {
        return medEnvolvimentoAluno;
    }

    public void setMedEnvolvimentoAluno(double medEnvolvimentoAluno) {
        this.medEnvolvimentoAluno = medEnvolvimentoAluno;
    }

    public List<String> getEncerramentos() {
        return encerramentos;
    }

    public void setEncerramentos(List<String> encerramentos) {
        this.encerramentos = encerramentos;
    }

    public List<Publicacao> getPublicacoes() {
        return publicacoes;
    }

    public void setPublicacoes(List<Publicacao> publicacoes) {
        this.publicacoes = publicacoes;
    }

    public double getMedAproveitamentoTurma() {
        return medAproveitamentoTurma;
    }

    public void setMedAproveitamentoTurma(double medAproveitamentoTurma) {
        this.medAproveitamentoTurma = medAproveitamentoTurma;
    }

    public double getMedEnvolvimentoTurma() {
        return medEnvolvimentoTurma;
    }

    public void setMedEnvolvimentoTurma(double medEnvolvimentoTurma) {
        this.medEnvolvimentoTurma = medEnvolvimentoTurma;
    }

    public List<Tema> getTemasCriticos() {
        return temasCriticos;
    }

    public void setTemasCriticos(List<Tema> temasCriticos) {
        this.temasCriticos = temasCriticos;
    }

    public List<Questao> getQuestoesCriticas() {
        return questoesCriticas;
    }

    public void setQuestoesCriticas(List<Questao> questoesCriticas) {
        this.questoesCriticas = questoesCriticas;
    }
}
