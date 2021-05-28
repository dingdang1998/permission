package com.oket.getdata.config;

import com.oket.getdata.entity.DataBaseConfigInfoEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: getdata
 * @description: 数据库配置信息集合
 * @author: dzp
 * @create: 2021-05-12 09:16
 **/
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "customconfig")
public class DataBaseConfigInfo {
    List<DataBaseConfigInfoEntity> configInfoEntities;
}
