package com.zephyra.ai.domain.dto;

import com.zephyra.ai.domain.entity.ColumnInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author York
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColumnAddDTO {

    private Long tableId;

    private ColumnInfoEntity columnInfoEntity;
}
