package config;

import javax.sql.DataSource;
import model.entity.Disciplina;
import model.entity.Professor;
import model.entity.Questao;
import model.entity.Tag;
import model.entity.Usuario;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DBConfig {

    @Bean
    public SessionFactory sessionFactory() {
        return new LocalSessionFactoryBuilder(getDataSource())
                .addAnnotatedClasses(Usuario.class)
                .addAnnotatedClasses(Disciplina.class)
                .addAnnotatedClasses(Tag.class)
                .addAnnotatedClasses(Questao.class)
                .addAnnotatedClasses(Professor.class)
                .buildSessionFactory();
    }

    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/intelequiz?autoReconnect=true&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }
}