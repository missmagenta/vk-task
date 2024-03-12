package com.example.task.configuration;

import org.jooq.DSLContext;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

@Configuration
public class JooqConfig {

    @Bean
    public DSLContext dslContext(@Qualifier("postgresDataSource") DataSource postgresDataSource) {
        DefaultConfiguration configuration = new DefaultConfiguration();
        configuration.set(new DataSourceConnectionProvider(new TransactionAwareDataSourceProxy(postgresDataSource)));
        return org.jooq.impl.DSL.using(configuration);
    }
}