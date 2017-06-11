package util;

import entidade.Aluno;
import entidade.Disciplina;
import enums.TurnoTurma;

public class RankingVO {

    private int id;
    
    private int ano;

    private int semestre;

    private String letra;

    private TurnoTurma turno;

    private Disciplina disciplina;
    
    private Aluno aluno;
    
    private double pontuacao;

    private double aproveitamento;

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public TurnoTurma getTurno() {
        return turno;
    }

    public void setTurno(TurnoTurma turno) {
        this.turno = turno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public double getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(double pontuacao) {
        this.pontuacao = pontuacao;
    }

    public double getAproveitamento() {
        return aproveitamento;
    }

    public void setAproveitamento(double aproveitamento) {
        this.aproveitamento = aproveitamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
