package com.zephyra.ai.decorator;

import lombok.Data;

/**
 * 字段结构定义
 */
@Data
public class ColumnSchema {

    /**
     * 字段名
     */
    private String name;

    /**
     * 字段类型（AI 层定义的逻辑类型）
     */
    private String logicalType;

    /**
     * SQL 映射类型
     */
    private String sqlType;

    /**
     * 是否主键
     */
    private boolean primaryKey;

    /**
     * 是否可为空
     */
    private boolean nullable = true;

    /**
     * 默认值
     */
    private String defaultValue;

    /**
     * 字段注释
     */
    private String comment;
}
