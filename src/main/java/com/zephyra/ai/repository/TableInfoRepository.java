package com.zephyra.ai.repository;

import com.zephyra.ai.domain.entity.TableInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author York
 */
@Repository
public interface TableInfoRepository extends JpaRepository<TableInfoEntity, Long> {

    List<TableInfoEntity> findAllByIdIn(List<Long> tableIdList);

    void deleteAllByIdIn(List<Long> tableIdList);
}
