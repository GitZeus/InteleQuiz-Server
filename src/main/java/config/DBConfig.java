package config;

import javax.sql.DataSource;
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
                .buildSessionFactory();
    }

    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/intelequiz");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }
}