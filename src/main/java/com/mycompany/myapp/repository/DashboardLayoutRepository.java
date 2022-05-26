package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DashboardLayout;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the DashboardLayout entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DashboardLayoutRepository extends MongoRepository<DashboardLayout, String> {}
