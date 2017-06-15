package configuracao;

import entidade.Aluno;
import entidade.Disciplina;
import entidade.Gabarito;
import entidade.Professor;
import entidade.Questao;
import entidade.Quiz;
import entidade.Resposta;
import entidade.Tema;
import entidade.Treino;
import entidade.Turma;
import entidade.Publicacao;
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
                    .addAnnotatedClasses(Publicacao.class)
                    .addAnnotatedClasses(Gabarito.class)
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
//                    .setProperty("hibernate.show_sql", "true")
//                    .setProperty("hibernate.format_sql", "true")
                    .buildSessionFactory();
            return sessionFactory;
        } catch (Throwable e) {
            e.printStackTrace();
            throw new ITQException("Erro ao criar SessionFactory");
        }
    }

    @Bean
    public DataSource getDataSource() throws ITQException {
        try {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");

//            dataSource.setUrl("jdbc:mysql://localhost:3306/intelequiz?autoReconnect=true&useSSL=false");
//            dataSource.setUsername("root");
//            dataSource.setPassword("root");
            
//            dataSource.setUrl("jdbc:mysql://localhost:3306/intelequiz?autoReconnect=true&useSSL=false");
//            dataSource.setUsername("root");
//            dataSource.setPassword("uniceub");

//              HEROKU - CLEARDB
//            dataSource.setUrl("jdbc:mysql://us-cdbr-iron-east-03.cleardb.net/heroku_06cfd2d6b0966bc?reconnect=true");
//            dataSource.setUsername("b0039843b7fefb");
//            dataSource.setPassword("c7fb64f2");

//              HEROKU - JAWSDB
//            dataSource.setUrl("jdbc:mysql://lg7j30weuqckmw07.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/b6nl9o7q85wt5wn2?reconnect=true");
//            dataSource.setUsername("qo4c3m5smcyo26mo");
//            dataSource.setPassword("swj2h852h99d4ohs");
            
//              HELIOHOST
            dataSource.setUrl("jdbc:mysql://johnny.heliohost.org:3306/efreitas_intelequiz?reconnect=true");
            dataSource.setUsername("efreitas_admin");
            dataSource.setPassword("Intel6785");

            return dataSource;
        } catch (Throwable e) {
            e.printStackTrace();
            throw new ITQException("Erro ao acessar o Banco de Dados");
        }
    }
}
