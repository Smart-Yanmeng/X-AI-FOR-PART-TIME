package com.zephyra.ai.service;

import java.util.List;

/**
 * @author York
 */
public interface IAliAiService {

    String createDatabaseJson(String demand);

    String updateTableInfoByAi(String demand, List<Long> tableIdList);
}
