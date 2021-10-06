package com.carshop.admin.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;


}
