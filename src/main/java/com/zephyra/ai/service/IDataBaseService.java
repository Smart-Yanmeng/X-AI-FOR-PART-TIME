package com.zephyra.ai.service;

import com.zephyra.ai.domain.entity.ColumnInfoEntity;
import com.zephyra.ai.domain.entity.TableInfoEntity;

import java.util.List;

/**
 * @author York
 */
public interface IDataBaseService {

    List<TableInfoEntity> getTableInfo();

    List<TableInfoEntity> getTableInfoById(List<Long> tableIdList);

    TableInfoEntity createTable(TableInfoEntity tableInfoEntity);

    TableInfoEntity updateTable(TableInfoEntity tableInfoEntity);

    void deleteTable(Long id);

    List<ColumnInfoEntity> getColumnInfo(Long tableId);

    ColumnInfoEntity createColumn(Long tableId, ColumnInfoEntity columnInfoEntity);

    ColumnInfoEntity updateColumn(ColumnInfoEntity columnInfoEntity);

    void deleteColumn(Long id);
}
