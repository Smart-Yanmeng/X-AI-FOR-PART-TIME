package com.zephyra.ai.service.impl;

import com.zephyra.ai.domain.entity.ColumnInfoEntity;
import com.zephyra.ai.domain.entity.TableInfoEntity;
import com.zephyra.ai.exception.SystemBizException;
import com.zephyra.ai.repository.ColumnInfoRepository;
import com.zephyra.ai.repository.TableInfoRepository;
import com.zephyra.ai.service.IDataBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author York
 */
@Service
@RequiredArgsConstructor
public class DataBaseServiceServiceImpl implements IDataBaseService {

    private final TableInfoRepository tableInfoRepository;

    private final ColumnInfoRepository columnInfoRepository;

    @Override
    public List<TableInfoEntity> getTableInfo() {
        return tableInfoRepository.findAll();
    }

    @Override
    public List<TableInfoEntity> getTableInfoById(List<Long> tableIdList) {
        return tableInfoRepository.findAllByIdIn(tableIdList);
    }

    @Override
    public TableInfoEntity createTable(TableInfoEntity tableInfoEntity) {
        if (tableInfoEntity.getColumns() != null) {
            tableInfoEntity.getColumns().forEach(column -> column.setTable(tableInfoEntity));
        }

        return tableInfoRepository.save(tableInfoEntity);
    }

    @Override
    public TableInfoEntity updateTable(TableInfoEntity tableInfoEntity) {
        return tableInfoRepository.findById(tableInfoEntity.getId()).map(table -> {
            table.setTableName(tableInfoEntity.getTableName());
            table.setComment(tableInfoEntity.getComment());
            table.setPrimaryKey(tableInfoEntity.getPrimaryKey());

            return tableInfoRepository.save(table);
        }).orElseThrow(() -> new RuntimeException("Table not found"));
    }

    @Override
    public void deleteTable(Long id) {
        tableInfoRepository.deleteById(id);
    }

    @Override
    public List<ColumnInfoEntity> getColumnInfo(Long tableId) {
        return columnInfoRepository.findAllByTableId(tableId);
    }

    @Override
    public ColumnInfoEntity createColumn(Long tableId, ColumnInfoEntity columnInfoEntity) {
        TableInfoEntity table = tableInfoRepository.findById(tableId)
                .orElseThrow(() -> new SystemBizException("Table not found"));
        columnInfoEntity.setTable(table);

        return columnInfoRepository.save(columnInfoEntity);
    }

    @Override
    public ColumnInfoEntity updateColumn(ColumnInfoEntity columnInfoEntity) {
        return columnInfoRepository.findById(columnInfoEntity.getId()).map(column -> {
            column.setName(columnInfoEntity.getName());
            column.setLogicalType(columnInfoEntity.getLogicalType());
            column.setSqlType(columnInfoEntity.getSqlType());
            column.setPrimaryKey(columnInfoEntity.getPrimaryKey());
            column.setNullable(columnInfoEntity.getNullable());
            column.setDefaultValue(columnInfoEntity.getDefaultValue());
            column.setForeignTable(columnInfoEntity.getForeignTable());
            column.setForeignColumn(columnInfoEntity.getForeignColumn());

            return columnInfoRepository.save(column);
        }).orElseThrow(() -> new SystemBizException("Column not found"));
    }

    @Override
    public void deleteColumn(Long id) {
        columnInfoRepository.deleteById(id);
    }
}
