package org.playground.login.playground;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.playground.login.playground.filter.LoginFilter;
import org.playground.login.playground.filter.RestFilter;
import org.playground.login.playground.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;

@Configuration
@ComponentScan
@EnableTransactionManagement
public class ConfigBean {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @Bean
    public DSLContext createDSLContext() {
        return DSL.using(dataSource, SQLDialect.POSTGRES);
    }

    @Autowired
    private AutowireCapableBeanFactory beanFactory;


    @Bean(name = "txManager")
    public PlatformTransactionManager createTranactionManger() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(jdbcTemplate.getDataSource());
        return transactionManager;
    }

    @Bean
    public FilterRegistrationBean loginFilterBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        LoginFilter loginFilter = new LoginFilter();
        beanFactory.autowireBean(loginFilter);
        registration.setFilter(loginFilter);
        registration.addUrlPatterns("/playground/*");
        registration.setOrder(0);
        return registration;
    }

    @Bean
    public FilterRegistrationBean restFilterBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        RestFilter filter = new RestFilter();
        beanFactory.autowireBean(filter);
        registration.setFilter(filter);
        registration.addUrlPatterns("/rest/*");
        registration.setOrder(0);
        return registration;
    }


    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public UserSessionService configService() {
        return UserSessionService.getInstance();
    }
}
