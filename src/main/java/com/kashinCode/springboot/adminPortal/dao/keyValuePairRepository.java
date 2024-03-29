package com.kashinCode.springboot.adminPortal.dao;

import com.kashinCode.springboot.adminPortal.entity.KeyValuePair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface keyValuePairRepository extends JpaRepository<KeyValuePair, Long> {
    boolean existsByKeyName(String keyName);
}
