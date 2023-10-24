package com.kaido.config;

import com.kaido.internal.mybatis.AbstractMybatisConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author zhoujunlin
 * @date 2023/03/27
 **/
@Configuration
@MapperScan(basePackages = "com.kaido.repository.db.mapper.base", annotationClass = Mapper.class,
        sqlSessionFactoryRef = "baseSqlSessionFactory")
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
public class BaseMybatisConfiguration extends AbstractMybatisConfig {

    public static final String DATA_SOURCE_PROPERTIES = "baseDataSourceProperties";
    public static final String DATA_SOURCE = "baseDataSource";
    public static final String SQL_SESSION_FACTORY = "baseSqlSessionFactory";
    public static final String TRANSACTION_MANAGER = "baseTransactionManager";

    private static final String MAPPER_LOCATION = "classpath*:mybatis/base/*.xml";
    private static final String TYPE_ALIASES_PACKAGE = "com.kaido.repository.db.entity.base";

    @Override
    @Bean(DATA_SOURCE_PROPERTIES)
    @ConfigurationProperties("spring.datasource.base")
    protected DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Override
    @Bean(DATA_SOURCE)
    @ConfigurationProperties(prefix = "spring.datasource.base.hikari")
    public HikariDataSource dataSource(@Autowired @Qualifier(DATA_SOURCE_PROPERTIES) DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Override
    @Bean(SQL_SESSION_FACTORY)
    public SqlSessionFactory sqlSessionFactory(@Autowired @Qualifier(DATA_SOURCE) HikariDataSource dataSource) throws Exception {
        return buildSqlSessionFactory(dataSource);
    }

    @Override
    @Bean(TRANSACTION_MANAGER)
    public PlatformTransactionManager platformTransactionManager(@Autowired @Qualifier(DATA_SOURCE) HikariDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Override
    public String getMapperLocation() {
        return MAPPER_LOCATION;
    }

    @Override
    public String getTypeAliasesPackage() {
        return TYPE_ALIASES_PACKAGE;
    }

}
