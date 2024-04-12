package com.kashinCode.springboot.adminPortal.dao;

import com.kashinCode.springboot.adminPortal.entity.Environment;
import com.kashinCode.springboot.adminPortal.entity.KeyValuePair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface keyValuePairRepository extends JpaRepository<KeyValuePair, Long> {
    boolean existsByKeyName(String keyName);



    boolean existsByEnvironmentIdAndKeyName(Long environmentId, String keyName);

    boolean existsByEnvironmentId(Long environmentId);

//   public List<KeyValuePair> findByEnvironmentIdLike(String environmentId);

    List<KeyValuePair> findByEnvironmentId(Long environmentId);

    List<KeyValuePair> findByEnvironment(Environment Env);
}

