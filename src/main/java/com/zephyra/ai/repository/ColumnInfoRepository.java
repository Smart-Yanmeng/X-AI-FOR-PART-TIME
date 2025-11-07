package com.zephyra.ai.repository;

import com.zephyra.ai.domain.entity.ColumnInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author York
 */
@Repository
public interface ColumnInfoRepository extends JpaRepository<ColumnInfoEntity, Long> {
    List<ColumnInfoEntity> findAllByTableId(Long tableId);
}
