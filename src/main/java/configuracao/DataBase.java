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

@Configuration
@EnableTransactionManagement
public class DataBase {

    @Bean
    public SessionFactory sessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            sessionFactory = new LocalSessionFactoryBuilder(getDataSource())
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        try {
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/intelequiz?autoReconnect=true&useSSL=false");
            dataSource.setUsername("root");
            dataSource.setPassword("root");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }
}
