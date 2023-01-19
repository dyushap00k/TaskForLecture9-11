package com.config;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class ConfigurationSpring {

    @Bean
    public DataSource dataSource() {
        try (FileReader fileReader = new FileReader("src/main/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(fileReader);
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser(properties.getProperty("dataSource.user"));
            dataSource.setPassword(properties.getProperty("dataSource.password"));
            dataSource.setURL(properties.getProperty("dataSource.url"));
            return dataSource;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
