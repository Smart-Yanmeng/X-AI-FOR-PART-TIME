package com.zephyra.ai.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zephyra.ai.domain.entity.ColumnInfoEntity;
import com.zephyra.ai.domain.entity.TableInfoEntity;
import com.zephyra.ai.repository.ColumnInfoRepository;
import com.zephyra.ai.repository.TableInfoRepository;
import com.zephyra.ai.service.IAliAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.lang.System;
import java.util.List;
import java.util.Map;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;

/**
 * @author York
 */
@Service
@RequiredArgsConstructor
public class AliAiServiceImpl implements IAliAiService {

    private final ObjectMapper MAPPER = new ObjectMapper();

    private final TableInfoRepository tableInfoRepository;

    private final ColumnInfoRepository columnInfoRepository;

    @Override
    public String createDatabaseJson(String demand) {
        try {
            GenerationResult result = callWithMessage(demand);
            String content = result.getOutput().getChoices().getFirst().getMessage().getContent();
            System.out.println(content);

            List<Map<String, Object>> tables = MAPPER.readValue(content, List.class);
            saveToH2(tables);

            System.out.println("tables -> " + tables);

            return content;
        } catch (ApiException | NoApiKeyException | InputRequiredException e) {
            System.err.println("错误信息：" + e.getMessage());
            System.out.println("请参考文档：https://help.aliyun.com/zh/model-studio/developer-reference/error-code");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static GenerationResult callWithMessage(String demand) throws ApiException, NoApiKeyException, InputRequiredException {
        Generation gen = new Generation();
        Message systemMsg = Message.builder()
                .role(Role.SYSTEM.getValue())
                .content("""
                        你是一名专业的数据库设计专家，请根据我提供的业务需求，输出数据库表结构定义，格式必须为 JSON 数组，严格使用以下格式，并且需要能直接从以下格式转换成类的格式：
                        
                        [
                            {
                                "tableName": "xxx",      // 表名，建议使用下划线风格
                                "comment": "xxx",        // 表注释
                                "primaryKey": "id",      // 主键字段名
                                "columns": [
                                    {
                                        "name": "xxx",                   // 字段名称
                                        "logicalType": "string|integer|number|boolean|datetime|date|text|json|enum", \s
                                        "sqlType": "VARCHAR(64)|BIGINT|INT|TEXT|TIMESTAMP...",\s
                                        "primaryKey": true|false,
                                        "nullable": true|false,
                                        "defaultValue": "xxx",           // 可以为空
                                        "foreignKey": {                  // 如果是外键才填写
                                            "referenceTable": "xxx",
                                            "referenceColumn": "xxx"
                                        }
                                    }
                                ]
                            }
                        ]
                        
                        ⚠️ 输出要求：
                        1. 必须是 JSON 格式，无多余注释、无 Markdown 代码块标记（不要 ```）；
                        2. 必须包含表名、表注释、字段列表、主键、字段类型；
                        3. 字段类型 logicalType 与 sqlType 要符合业务语义；
                        4. 存在外键关系的字段必须标注 foreignKey；
                        5. 只输出 JSON，不要解释文字；
                        
                        请根据以下业务需求生成：
                        {业务需求内容}
                        """)
                .build();
        Message userMsg = Message.builder()
                .role(Role.USER.getValue())
                .content(demand)
                .build();
        GenerationParam param = GenerationParam.builder()
                // 若没有配置环境变量，请用阿里云百炼API Key将下行替换为：.apiKey("sk-xxx")
                .apiKey("sk-66512847f8bf460a9f0aee09e2d3c869")
                // 模型列表：https://help.aliyun.com/zh/model-studio/getting-started/models
                .model("qwen-plus")
                .messages(Arrays.asList(systemMsg, userMsg))
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                .build();
        return gen.call(param);
    }

    public void saveToH2(List<Map<String, Object>> tables) {
        for (Map<String, Object> tableMap : tables) {
            // 保存 table_info
            TableInfoEntity table = new TableInfoEntity();
            table.setTableName((String) tableMap.get("tableName"));
            table.setComment((String) tableMap.get("comment"));
            table.setPrimaryKey((String) tableMap.get("primaryKey"));
            tableInfoRepository.save(table);

            Long tableId = table.getId();

            // 保存 column_info
            List<Map<String, Object>> columns = (List<Map<String, Object>>) tableMap.get("columns");
            for (Map<String, Object> col : columns) {
                ColumnInfoEntity column = new ColumnInfoEntity();
                column.setTableId(tableId);
                column.setName((String) col.get("name"));
                column.setLogicalType((String) col.get("logicalType"));
                column.setSqlType((String) col.get("sqlType"));
                column.setPrimaryKey((Boolean) col.get("primaryKey"));
                column.setNullable((Boolean) col.get("nullable"));
                column.setDefaultValue((String) col.get("defaultValue"));

                Map<String, String> fk = (Map<String, String>) col.get("foreignKey");
                if (fk != null) {
                    column.setForeignTable(fk.get("referenceTable"));
                    column.setForeignColumn(fk.get("referenceColumn"));
                }

                columnInfoRepository.save(column);
            }
        }
    }
}
