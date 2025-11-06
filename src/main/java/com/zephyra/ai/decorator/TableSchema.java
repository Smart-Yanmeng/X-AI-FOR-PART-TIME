package com.zephyra.ai.decorator;

import lombok.Data;
import java.util.*;

/**
 * 表结构信息（由 AI 模块生成并传入本模块缓存）
 * @author yorky
 */
@Data
public class TableSchema {

    /** 表名 */
    private String tableName;
    /** 表注释（可选） */
    private String comment;
    /** 字段定义 */
    private List<ColumnSchema> columns = new ArrayList<>();
    /** 主键字段名（可选） */
    private String primaryKey;
    /** 索引字段 */
    private List<String> indexes = new ArrayList<>();

    /**
     * 根据字段名快速索引列
     */
    public Optional<ColumnSchema> getColumn(String name) {
        return columns.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    /**
     * 获取所有字段名
     */
    public List<String> getFieldNames() {
        return columns.stream().map(ColumnSchema::getName).toList();
    }
}
