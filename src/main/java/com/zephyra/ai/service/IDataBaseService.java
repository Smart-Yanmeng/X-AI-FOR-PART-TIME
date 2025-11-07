package com.zephyra.ai.service;

import com.zephyra.ai.domain.entity.ColumnInfoEntity;
import com.zephyra.ai.domain.entity.TableInfoEntity;

import java.util.List;

/**
 * @author York
 */
public interface IDataBaseService {

    List<TableInfoEntity> getTableInfo();

    List<ColumnInfoEntity> getColumnInfo(Long tableId);
}
