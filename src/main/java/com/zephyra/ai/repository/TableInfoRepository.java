package com.zephyra.ai.repository;

import com.zephyra.ai.domain.entity.TableInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author York
 */
@Repository
public interface TableInfoRepository extends JpaRepository<TableInfoEntity, Long> {
}
