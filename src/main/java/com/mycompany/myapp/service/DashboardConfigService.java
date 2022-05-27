package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DashboardConfigDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DashboardConfig}.
 */
public interface DashboardConfigService {
    /**
     * Save a dashboardConfig.
     *
     * @param dashboardConfigDTO the entity to save.
     * @return the persisted entity.
     */
    DashboardConfigDTO save(DashboardConfigDTO dashboardConfigDTO);

    /**
     * Updates a dashboardConfig.
     *
     * @param dashboardConfigDTO the entity to update.
     * @return the persisted entity.
     */
    DashboardConfigDTO update(DashboardConfigDTO dashboardConfigDTO);

    /**
     * Partially updates a dashboardConfig.
     *
     * @param dashboardConfigDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DashboardConfigDTO> partialUpdate(DashboardConfigDTO dashboardConfigDTO);

    /**
     * Get all the dashboardConfigs.
     *
     * @return the list of entities.
     */
    List<DashboardConfigDTO> findAll();

    /**
     * Get the "id" dashboardConfig.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DashboardConfigDTO> findOne(String id);

    /**
     * Delete the "id" dashboardConfig.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
