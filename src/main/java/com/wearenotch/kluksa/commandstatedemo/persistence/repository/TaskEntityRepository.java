package com.wearenotch.kluksa.commandstatedemo.persistence.repository;

import com.wearenotch.kluksa.commandstatedemo.persistence.domain.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskEntityRepository extends JpaRepository<TaskEntity, Long> {
}
