package configuracao;

import entidade.Aluno;
import entidade.Disciplina;
import entidade.Professor;
import entidade.Questao;
import entidade.Quiz;
import entidade.Resposta;
import entidade.Tema;
import entidade.Treino;
import entidade.Turma;
import entidade.TurmaQuiz;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import util.ITQException;

@Configuration
@EnableTransactionManagement
public class DataBase {

    @Bean
    public SessionFactory sessionFactory() throws ITQException {
        try {
            SessionFactory sessionFactory = new LocalSessionFactoryBuilder(getDataSource())
                    .addAnnotatedClasses(Aluno.class)
                    .addAnnotatedClasses(Disciplina.class)
                    .addAnnotatedClasses(Professor.class)
                    .addAnnotatedClasses(Questao.class)
                    .addAnnotatedClasses(Quiz.class)
                    .addAnnotatedClasses(Resposta.class)
                    .addAnnotatedClasses(Tema.class)
                    .addAnnotatedClasses(Treino.class)
                    .addAnnotatedClasses(Turma.class)
                    .addAnnotatedClasses(TurmaQuiz.class)
                    .setProperty("hibernate.show_sql", "true")
                    .setProperty("hibernate.format_sql", "true")
                    .buildSessionFactory();
            return sessionFactory;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    @Bean
    public DataSource getDataSource() throws ITQException {
        try {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/intelequiz?autoReconnect=true&useSSL=false");
            dataSource.setUsername("root");
            dataSource.setPassword("root");
            return dataSource;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
}
