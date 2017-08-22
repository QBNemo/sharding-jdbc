package com.study.dangdang.sharding.jdbc.algorithm;

import java.util.Collection;
import java.util.LinkedHashSet;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;

public class UserSingleKeyTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Integer>{

    /**
     * sql 中 = 操作时，table的映射  只对分表的key起作用,columnName
     * insert 多value走doEqualSharding 有坑 需要先构造<dbkey, <tablekey, List<>>再插入
     */
    public String doEqualSharding(Collection<String> tableNames, ShardingValue<Integer> shardingValue) {
    	// [t_user_0, t_user_1, t_user_2]
    	// ShardingValue(columnName=user_id, value=3, values=[], valueRange=null)
        for (String each : tableNames) {
            if (each.endsWith(shardingValue.getValue() % 3 + "")) {
                return each;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * sql 中 in 操作时，table的映射
     */
    public Collection<String> doInSharding(Collection<String> tableNames, ShardingValue<Integer> shardingValue) {
    	// [t_user_0, t_user_1, t_user_2]
    	// ShardingValue(columnName=user_id, value=null, values=[12, 13, 14], valueRange=null)
    	System.out.println(tableNames);
    	System.out.println(shardingValue);
        Collection<String> result = new LinkedHashSet<String>(tableNames.size());
        for (Integer value : shardingValue.getValues()) {
            for (String tableName : tableNames) {
                if (tableName.endsWith(value % 3 + "")) {
                    result.add(tableName);
                }
            }
        }
        return result;
    }

    /**
     * sql 中 between 操作时，table的映射
     */
    public Collection<String> doBetweenSharding(Collection<String> tableNames, ShardingValue<Integer> shardingValue) {
    	// [t_user_0, t_user_1, t_user_2]
    	// ShardingValue(columnName=user_id, value=null, values=[], valueRange=[1‥3])
    	System.out.println(tableNames);
    	System.out.println(shardingValue);
        Collection<String> result = new LinkedHashSet<String>(tableNames.size());
        Range<Integer> range = (Range<Integer>) shardingValue.getValueRange();
        for (Integer i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String each : tableNames) {
                if (each.endsWith(i % 3 + "")) {
                    result.add(each);
                }
            }
        }
        return result;
    } 
}
