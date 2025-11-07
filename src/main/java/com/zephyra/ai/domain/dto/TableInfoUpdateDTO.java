package com.zephyra.ai.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author York
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableInfoUpdateDTO {

    private String demand;

    private List<Long> tableInfoIdList;
}
