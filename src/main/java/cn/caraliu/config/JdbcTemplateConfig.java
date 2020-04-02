package cn.caraliu.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;

@Configuration
public class JdbcTemplateConfig {

    @Bean(name = "dataSource0")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.multiDataSource0")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dataSource1")
    @ConfigurationProperties(prefix="spring.datasource.multiDataSource1")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    JdbcTemplate jdbcTemplate0(@Qualifier("dataSource0") DataSource dataSource0) {
        return new JdbcTemplate(dataSource0);
    }

    @Bean
    JdbcTemplate jdbcTemplate1(@Qualifier("dataSource1") DataSource dataSource1) {
        return new JdbcTemplate(dataSource1);
    }

    /**
     * 注入事物管理器
     * @return
     */
    @Bean(name = "rttm")
    public JtaTransactionManager regTransactionManager () {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        UserTransaction userTransaction = new UserTransactionImp();
        return new JtaTransactionManager(userTransaction, userTransactionManager);
    }

}