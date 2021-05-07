package com.example.demo.config;


import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import io.shardingjdbc.core.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingjdbc.core.jdbc.core.datasource.ShardingDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 */
@Configuration
@MapperScan(basePackages = "com.example.demo.mapper", sqlSessionTemplateRef = "testSqlSessionTemplate")
public class DataSourceConfig {

    @Autowired
    private CustomTableRuleConfiguration customTableRuleConfiguration;

    @Bean(name = "shardingDataSource")
    DataSource getShardingDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig;
        shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.setTableRuleConfigs(customTableRuleConfiguration.getTableRuleConfigurations());
        shardingRuleConfig.setBindingTableGroups(customTableRuleConfiguration.getBindingTableGroups());
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("account", CustomerTableShardingAlgorithm.class.getName()));
        return new ShardingDataSource(shardingRuleConfig.build(createDataSourceMap()));
    }

    /**
     * 需要手动配置事务管理器
     *
     * @param shardingDataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource shardingDataSource) {
        return new DataSourceTransactionManager(shardingDataSource);
    }

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory(DataSource shardingDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(shardingDataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }

    @Bean
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 创建数据源
     * 一个库对应一个数据源
     *
     * @return
     */
    private Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        result.put("user_0", createDataSource());
        return result;
    }

//    private DataSource createDataSource(final String dataSourceName) {
//        BasicDataSource result = new BasicDataSource();
//        result.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
//        result.setUrl(String.format("jdbc:mysql://localhost:3306/%s", dataSourceName));
//        result.setUsername("root");
//        result.setPassword("root123");
//        return result;
//    }

    private DataSource createDataSource() {
        BasicDataSource result = new BasicDataSource();
        result.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
        result.setUrl("jdbc:mysql://localhost:3306/user_0");
        result.setUsername("root");
        result.setPassword("root123");
        return result;
    }
}
