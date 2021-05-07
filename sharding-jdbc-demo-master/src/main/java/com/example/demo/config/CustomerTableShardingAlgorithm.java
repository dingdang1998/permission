package com.example.demo.config;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

/**
 * 自定义分表算法
 *
 * @author dzp
 */
public class CustomerTableShardingAlgorithm implements PreciseShardingAlgorithm<String> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        for (String each : collection) {
            if (each.endsWith(Integer.parseInt(preciseShardingValue.getValue()) % 2 + "")) {
                return each;
            }
        }
        throw new IllegalArgumentException();
    }
}