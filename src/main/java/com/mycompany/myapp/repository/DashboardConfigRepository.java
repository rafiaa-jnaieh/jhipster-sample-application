package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DashboardConfig;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the DashboardConfig entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DashboardConfigRepository extends MongoRepository<DashboardConfig, String> {}
