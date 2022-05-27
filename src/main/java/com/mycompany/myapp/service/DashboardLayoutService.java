package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DashboardLayoutDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DashboardLayout}.
 */
public interface DashboardLayoutService {
    /**
     * Save a dashboardLayout.
     *
     * @param dashboardLayoutDTO the entity to save.
     * @return the persisted entity.
     */
    DashboardLayoutDTO save(DashboardLayoutDTO dashboardLayoutDTO);

    /**
     * Updates a dashboardLayout.
     *
     * @param dashboardLayoutDTO the entity to update.
     * @return the persisted entity.
     */
    DashboardLayoutDTO update(DashboardLayoutDTO dashboardLayoutDTO);

    /**
     * Partially updates a dashboardLayout.
     *
     * @param dashboardLayoutDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DashboardLayoutDTO> partialUpdate(DashboardLayoutDTO dashboardLayoutDTO);

    /**
     * Get all the dashboardLayouts.
     *
     * @return the list of entities.
     */
    List<DashboardLayoutDTO> findAll();

    /**
     * Get the "id" dashboardLayout.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DashboardLayoutDTO> findOne(String id);

    /**
     * Delete the "id" dashboardLayout.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
