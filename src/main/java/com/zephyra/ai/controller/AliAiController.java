package com.zephyra.ai.controller;

import com.zephyra.ai.domain.vo.R;
import com.zephyra.ai.service.IAliAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author York
 */
@RestController
@RequestMapping("/ali-ai")
@RequiredArgsConstructor
public class AliAiController {

    private final IAliAiService aliAiService;

    @GetMapping("/create-database")
    public R<String> createDatabase(String demand) {
        return R.success(aliAiService.createDatabaseJson(demand));
    }
}
