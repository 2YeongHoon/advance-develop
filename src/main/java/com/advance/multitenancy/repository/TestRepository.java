package com.advance.multitenancy.repository;

import com.advance.multitenancy.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {

}
