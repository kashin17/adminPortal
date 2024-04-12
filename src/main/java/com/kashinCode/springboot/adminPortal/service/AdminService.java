package com.kashinCode.springboot.adminPortal.service;

import com.kashinCode.springboot.adminPortal.entity.Environment;
import com.kashinCode.springboot.adminPortal.entity.KeyValuePair;

import java.util.List;

public interface AdminService {

    List<KeyValuePair> getAll();

//    List<KeyValuePair> findByEnvironmentIdLike(String environmentId);

    List<KeyValuePair> findByEnvironmentId(Long environmentId);

    List<KeyValuePair> findByEnvironment(Environment Env);



    KeyValuePair getById(long theId);

    KeyValuePair save(KeyValuePair theKeyValuePair);

    boolean existByKeyName(String theKey);

    boolean existsByEnvironmentIdAndKeyName(Long environmentId, String keyName);

    boolean existsByEnvironmentId(Long environmentId);

    void deleteById(long theId);
}
