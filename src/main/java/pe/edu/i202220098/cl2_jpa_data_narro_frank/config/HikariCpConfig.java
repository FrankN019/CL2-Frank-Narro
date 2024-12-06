package pe.edu.i202220098.cl2_jpa_data_narro_frank.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HikariCpConfig {


    @Value("${DB_SAKILA_URL}")
    private String dbSakilaUrl;

    @Value("${DB_SAKILA_USER}")
    private String dbSakilaUser;

    @Value("${DB_SAKILA_PASS}")
    private String dbSakilaPass;

    @Value("${DB_SAKILA_DRIVER}")
    private String dbSakilaDriver;


    @Bean
    public HikariDataSource hikariDataSource() {

        HikariConfig hikariConfig = buildHikariConfig();


        System.out.println("###### HikariCP initialized ######");
        return new HikariDataSource(hikariConfig);
    }


    private HikariConfig buildHikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();


        hikariConfig.setJdbcUrl(dbSakilaUrl);
        hikariConfig.setUsername(dbSakilaUser);
        hikariConfig.setPassword(dbSakilaPass);
        hikariConfig.setDriverClassName(dbSakilaDriver);


        hikariConfig.setMaximumPoolSize(20);
        hikariConfig.setMinimumIdle(5);
        hikariConfig.setIdleTimeout(300000); // 5 minutos
        hikariConfig.setConnectionTimeout(30000); // 30 segundos

        return hikariConfig;
    }
}
