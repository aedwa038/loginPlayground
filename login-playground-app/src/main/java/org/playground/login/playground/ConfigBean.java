package org.playground.login.playground;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class ConfigBean {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    public DSLContext createDSLContext() {
        return DSL.using(jdbcTemplate.getDataSource(), SQLDialect.POSTGRES);
    }
}
