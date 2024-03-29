package com.kashinCode.springboot.adminPortal.service;

import com.kashinCode.springboot.adminPortal.entity.KeyValuePair;

import java.util.List;

public interface AdminService {

    List<KeyValuePair> getAll();

    KeyValuePair getById(long theId);

    KeyValuePair save(KeyValuePair theKeyValuePair);

    boolean existByKeyName(String theKey);

    void deleteById(long theId);
}
