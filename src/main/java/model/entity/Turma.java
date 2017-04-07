package model.entity;

import java.util.ArrayList;
import java.util.List;

public class Turma {
    
    public Turma(Professor professor, Disciplina disciplina){
        this.professor = professor;
        this.disciplina = disciplina;
    }
    
    private Integer id;
    private Professor professor;
    private Disciplina disciplina;
    private String turno = "Noturno";
    private String semestre = "1°";
    private String ano = "2017";
    private String letra = "UN";
    private List<Aluno> alunos = new ArrayList<Aluno>();
    
    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }
}