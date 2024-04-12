package com.kashinCode.springboot.adminPortal.dao;

import com.kashinCode.springboot.adminPortal.entity.Environment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface environmentRepository extends JpaRepository<Environment, Long> {

    Environment findByName(String name);
}
