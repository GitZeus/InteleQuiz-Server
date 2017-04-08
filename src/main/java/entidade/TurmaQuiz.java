package entidade;

import enums.StatusTurmaQuiz;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TB_TURMA_QUIZ")
public class TurmaQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @OneToOne
    Turma turma;
    
    @OneToOne
    Quiz quiz;

    @Temporal(TemporalType.TIMESTAMP)
    Date tsPublicacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date tsEncerramento;

    @Enumerated
    StatusTurmaQuiz status;
}
