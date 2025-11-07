package com.zephyra.ai.service.impl;

import com.zephyra.ai.domain.entity.ColumnInfoEntity;
import com.zephyra.ai.domain.entity.TableInfoEntity;
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
    public List<ColumnInfoEntity> getColumnInfo(Long tableId) {
        return columnInfoRepository.findAllByTableId(tableId);
    }
}
