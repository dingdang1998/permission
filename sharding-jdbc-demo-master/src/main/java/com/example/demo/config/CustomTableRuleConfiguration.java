package com.example.demo.config;

import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @program: demo
 * @description: 自定义表配置规则
 * @author: dzp
 * @create: 2021-05-07 10:08
 **/
@Component
public class CustomTableRuleConfiguration {
    /**
     * 表配置规则
     */
    private final Collection<TableRuleConfiguration> tableRuleConfigs = new LinkedList<>();
    /**
     * 绑定表组
     */
    private final Collection<String> bindingTableGroups = new LinkedList<>();

    public Collection<TableRuleConfiguration> getTableRuleConfigurations() {
        tableRuleConfigs.add(getUserInfoTableRuleConfiguration());
        tableRuleConfigs.add(getStudentInfoTableRuleConfiguration());
        return tableRuleConfigs;
    }

    public Collection<String> getBindingTableGroups() {
        bindingTableGroups.add("user_info");
        bindingTableGroups.add("student_info");
        return bindingTableGroups;
    }

    /**
     * user_info表配置规则
     *
     * @return
     */
    public TableRuleConfiguration getUserInfoTableRuleConfiguration() {
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
        //逻辑表
        orderTableRuleConfig.setLogicTable("user_info");
        //在分片的数据库中真实存在的物理表
        orderTableRuleConfig.setActualDataNodes("user_0.user_info_${0..1}");
        return orderTableRuleConfig;
    }

    /**
     * student_info表配置规则
     *
     * @return
     */
    public TableRuleConfiguration getStudentInfoTableRuleConfiguration() {
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
        orderTableRuleConfig.setLogicTable("student_info");
        orderTableRuleConfig.setActualDataNodes("user_0.student_info_${0..1}");
        return orderTableRuleConfig;
    }
}
