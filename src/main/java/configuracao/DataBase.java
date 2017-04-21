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
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    .setProperty("hibernate.show_sql", "true")
                    .setProperty("hibernate.format_sql", "true")
                    .buildSessionFactory();
            return sessionFactory;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("FAC: " + e.getMessage());
        }
    }

    @Bean
    public DataSource getDataSource() throws ITQException {
        try {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://us-cdbr-iron-east-03.cleardb.net/heroku_dbc8380a194f679?reconnect=true");
            dataSource.setUsername("bd20ed095bed99");
            dataSource.setPassword("7859d60c");
//            dataSource.setUrl(System.getenv("CLEARDB_URL"));
//            dataSource.setUsername(System.getenv("CLEARDB_USER"));
//            dataSource.setPassword(System.getenv("CLEARDB_PASS"));
//            dataSource.setUrl("jdbc:mysql://localhost:3306/intelequiz?autoReconnect=true&useSSL=false");
//            dataSource.setUsername("root");
//            dataSource.setPassword("root");
            return dataSource;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Falha ao acessar o Banco de Dados");
        }
    }
}
