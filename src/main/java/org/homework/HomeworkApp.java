package org.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;


/**
 * @author Roman.
 */
@SpringBootApplication
@PropertySource("classpath:homework.properties")
public class HomeworkApp {


    public static void main(String[] args) {
        SpringApplication.run(HomeworkApp.class, args);
    }

    @Bean(destroyMethod = "shutdown")
    public EmbeddedDatabase embeddedDatabase() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.H2).setName("QUESTIONS_DB").addScript("script.ddl"
        ).build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(embeddedDatabase());
    }


}
