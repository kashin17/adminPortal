package com.kashinCode.springboot.adminPortal.service;

import com.kashinCode.springboot.adminPortal.dao.keyValuePairRepository;
import com.kashinCode.springboot.adminPortal.entity.KeyValuePair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AdminServiceImpl implements AdminService{

    private keyValuePairRepository kvpRepository;

    public AdminServiceImpl(keyValuePairRepository theKvpRepository) {
        this.kvpRepository = theKvpRepository;
    }

    @Override
    public List<KeyValuePair> getAll() {
        return kvpRepository.findAll();
    }

    @Override
    public KeyValuePair getById(long theId) {
        Optional<KeyValuePair> result = kvpRepository.findById(theId);

        KeyValuePair thekvp = null;

        if (result.isPresent()) {
            thekvp = result.get();
        }
        else {
            // we didn't find the id of keyValuePair
            throw new RuntimeException("Did not find id - " + theId);
        }

        return thekvp;
    }

    @Override
    public KeyValuePair save(KeyValuePair theKeyValuePair) {
        return kvpRepository.save(theKeyValuePair);
    }

    public boolean existByKeyName(String theKey){
        return kvpRepository.existsByKeyName(theKey);
    }

    @Override
    public void deleteById(long theId) {
        kvpRepository.deleteById(theId);
    }
}
