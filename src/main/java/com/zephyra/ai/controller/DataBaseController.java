package com.zephyra.ai.controller;

import com.zephyra.ai.domain.entity.ColumnInfoEntity;
import com.zephyra.ai.domain.entity.TableInfoEntity;
import com.zephyra.ai.domain.vo.R;
import com.zephyra.ai.service.IDataBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/list-column")
    public R<List<ColumnInfoEntity>> getColumnInfo(@RequestParam("tableId") Long tableId) {
        return R.success(dataBaseService.getColumnInfo(tableId));
    }
}
