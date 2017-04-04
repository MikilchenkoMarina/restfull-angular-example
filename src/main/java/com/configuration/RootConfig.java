package com.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

import static org.springframework.orm.jpa.vendor.Database.POSTGRESQL;

/**
 * @author mmikilchenko on 01.04.2017.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.repository")
@EnableTransactionManagement
@ComponentScan(basePackages = "com", excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
public class RootConfig {


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://ec2-23-23-223-2.compute-1.amazonaws.com:5432/d1sadj6ppabskq");
        ds.setUsername("agdmalvghnqmud");
        ds.setPassword("d2e285aa20232132202e8bd9dbbe947fd8b8b058e9e908fbb0d722cdd0471767");
        Properties props = new Properties();
        props.setProperty("defaultAutoCommit", "true");
        ds.setConnectionProperties(props);
        return ds;
    }

    @Bean
    @Autowired
    public EntityManagerFactory entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lcemfBean =
                new LocalContainerEntityManagerFactoryBean();
        lcemfBean.setDataSource(dataSource);
        lcemfBean.setJpaVendorAdapter(jpaVendorAdapter);
        lcemfBean.setPackagesToScan("com.entity");

        Properties prop = new Properties();
        prop.setProperty("javax.persistence.validation.mode", "none");
        lcemfBean.setJpaProperties(prop);

        lcemfBean.afterPropertiesSet();
        return lcemfBean.getObject();
    }

    //HibertateJpaVendorAdapter

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(POSTGRESQL);
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
        jpaVendorAdapter.setGenerateDdl(false);
        return jpaVendorAdapter;
    }

    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
