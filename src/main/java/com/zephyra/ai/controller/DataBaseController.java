package com.zephyra.ai.controller;

import com.zephyra.ai.domain.dto.ColumnAddDTO;
import com.zephyra.ai.domain.entity.ColumnInfoEntity;
import com.zephyra.ai.domain.entity.TableInfoEntity;
import com.zephyra.ai.domain.vo.R;
import com.zephyra.ai.service.IDataBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author York
 */
@RestController
@RequestMapping("/database")
@RequiredArgsConstructor
public class DataBaseController {

    private final IDataBaseService dataBaseService;

    @GetMapping("/list-table")
    public R<List<TableInfoEntity>> getTableInfo() {
        return R.success(dataBaseService.getTableInfo());
    }

    @PostMapping("/list-table-by-id")
    public R<List<TableInfoEntity>> getTableInfoById(@RequestBody List<Long> tableIdList) {
        return R.success(dataBaseService.getTableInfoById(tableIdList));
    }

    @PostMapping("/create-table")
    public R<TableInfoEntity> createTableInfo(@RequestBody TableInfoEntity tableInfoEntity) {
        return R.success(dataBaseService.createTable(tableInfoEntity));
    }

    @PutMapping("/update-table")
    public R<TableInfoEntity> updateTableInfo(@RequestBody TableInfoEntity tableInfoEntity) {
        return R.success(dataBaseService.updateTable(tableInfoEntity));
    }

    @DeleteMapping("/delete-table")
    public R<String> deleteTableInfo(@RequestParam("tableId") Long tableId) {
        dataBaseService.deleteTable(tableId);

        return R.success("Delete table success.");
    }

    @GetMapping("/list-column")
    public R<List<ColumnInfoEntity>> getColumnInfo(@RequestParam("tableId") Long tableId) {
        return R.success(dataBaseService.getColumnInfo(tableId));
    }

    @PostMapping("/create-column")
    public R<ColumnInfoEntity> createColumnInfo(@RequestBody ColumnAddDTO columnAddDTO) {
        return R.success(dataBaseService.createColumn(columnAddDTO.getTableId(), columnAddDTO.getColumnInfoEntity()));
    }

    @PutMapping("/update-column")
    public R<ColumnInfoEntity> updateColumnInfo(@RequestBody ColumnInfoEntity columnInfoEntity) {
        return R.success(dataBaseService.updateColumn(columnInfoEntity));
    }

    @DeleteMapping("/delete-column")
    public R<String> deleteColumnInfo(@RequestParam("columnId") Long columnId) {
        dataBaseService.deleteColumn(columnId);

        return R.success("Delete column success.");
    }
}
