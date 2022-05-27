package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.DashboardLayout;
import com.mycompany.myapp.repository.DashboardLayoutRepository;
import com.mycompany.myapp.service.dto.DashboardLayoutDTO;
import com.mycompany.myapp.service.mapper.DashboardLayoutMapper;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Integration tests for the {@link DashboardLayoutResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DashboardLayoutResourceIT {

    private static final Integer DEFAULT_MIN_COLS = 1;
    private static final Integer UPDATED_MIN_COLS = 2;

    private static final Integer DEFAULT_MIN_ROWS = 1;
    private static final Integer UPDATED_MIN_ROWS = 2;

    private static final Integer DEFAULT_MAX_ROWS = 1;
    private static final Integer UPDATED_MAX_ROWS = 2;

    private static final String ENTITY_API_URL = "/api/dashboard-layouts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private DashboardLayoutRepository dashboardLayoutRepository;

    @Autowired
    private DashboardLayoutMapper dashboardLayoutMapper;

    @Autowired
    private MockMvc restDashboardLayoutMockMvc;

    private DashboardLayout dashboardLayout;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DashboardLayout createEntity() {
        DashboardLayout dashboardLayout = new DashboardLayout()
            .minCols(DEFAULT_MIN_COLS)
            .minRows(DEFAULT_MIN_ROWS)
            .maxRows(DEFAULT_MAX_ROWS);
        return dashboardLayout;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DashboardLayout createUpdatedEntity() {
        DashboardLayout dashboardLayout = new DashboardLayout()
            .minCols(UPDATED_MIN_COLS)
            .minRows(UPDATED_MIN_ROWS)
            .maxRows(UPDATED_MAX_ROWS);
        return dashboardLayout;
    }

    @BeforeEach
    public void initTest() {
        dashboardLayoutRepository.deleteAll();
        dashboardLayout = createEntity();
    }

    @Test
    void createDashboardLayout() throws Exception {
        int databaseSizeBeforeCreate = dashboardLayoutRepository.findAll().size();
        // Create the DashboardLayout
        DashboardLayoutDTO dashboardLayoutDTO = dashboardLayoutMapper.toDto(dashboardLayout);
        restDashboardLayoutMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dashboardLayoutDTO))
            )
            .andExpect(status().isCreated());

        // Validate the DashboardLayout in the database
        List<DashboardLayout> dashboardLayoutList = dashboardLayoutRepository.findAll();
        assertThat(dashboardLayoutList).hasSize(databaseSizeBeforeCreate + 1);
        DashboardLayout testDashboardLayout = dashboardLayoutList.get(dashboardLayoutList.size() - 1);
        assertThat(testDashboardLayout.getMinCols()).isEqualTo(DEFAULT_MIN_COLS);
        assertThat(testDashboardLayout.getMinRows()).isEqualTo(DEFAULT_MIN_ROWS);
        assertThat(testDashboardLayout.getMaxRows()).isEqualTo(DEFAULT_MAX_ROWS);
    }

    @Test
    void createDashboardLayoutWithExistingId() throws Exception {
        // Create the DashboardLayout with an existing ID
        dashboardLayout.setId("existing_id");
        DashboardLayoutDTO dashboardLayoutDTO = dashboardLayoutMapper.toDto(dashboardLayout);

        int databaseSizeBeforeCreate = dashboardLayoutRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDashboardLayoutMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dashboardLayoutDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DashboardLayout in the database
        List<DashboardLayout> dashboardLayoutList = dashboardLayoutRepository.findAll();
        assertThat(dashboardLayoutList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllDashboardLayouts() throws Exception {
        // Initialize the database
        dashboardLayout.setId(UUID.randomUUID().toString());
        dashboardLayoutRepository.save(dashboardLayout);

        // Get all the dashboardLayoutList
        restDashboardLayoutMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dashboardLayout.getId())))
            .andExpect(jsonPath("$.[*].minCols").value(hasItem(DEFAULT_MIN_COLS)))
            .andExpect(jsonPath("$.[*].minRows").value(hasItem(DEFAULT_MIN_ROWS)))
            .andExpect(jsonPath("$.[*].maxRows").value(hasItem(DEFAULT_MAX_ROWS)));
    }

    @Test
    void getDashboardLayout() throws Exception {
        // Initialize the database
        dashboardLayout.setId(UUID.randomUUID().toString());
        dashboardLayoutRepository.save(dashboardLayout);

        // Get the dashboardLayout
        restDashboardLayoutMockMvc
            .perform(get(ENTITY_API_URL_ID, dashboardLayout.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dashboardLayout.getId()))
            .andExpect(jsonPath("$.minCols").value(DEFAULT_MIN_COLS))
            .andExpect(jsonPath("$.minRows").value(DEFAULT_MIN_ROWS))
            .andExpect(jsonPath("$.maxRows").value(DEFAULT_MAX_ROWS));
    }

    @Test
    void getNonExistingDashboardLayout() throws Exception {
        // Get the dashboardLayout
        restDashboardLayoutMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putNewDashboardLayout() throws Exception {
        // Initialize the database
        dashboardLayout.setId(UUID.randomUUID().toString());
        dashboardLayoutRepository.save(dashboardLayout);

        int databaseSizeBeforeUpdate = dashboardLayoutRepository.findAll().size();

        // Update the dashboardLayout
        DashboardLayout updatedDashboardLayout = dashboardLayoutRepository.findById(dashboardLayout.getId()).get();
        updatedDashboardLayout.minCols(UPDATED_MIN_COLS).minRows(UPDATED_MIN_ROWS).maxRows(UPDATED_MAX_ROWS);
        DashboardLayoutDTO dashboardLayoutDTO = dashboardLayoutMapper.toDto(updatedDashboardLayout);

        restDashboardLayoutMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dashboardLayoutDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dashboardLayoutDTO))
            )
            .andExpect(status().isOk());

        // Validate the DashboardLayout in the database
        List<DashboardLayout> dashboardLayoutList = dashboardLayoutRepository.findAll();
        assertThat(dashboardLayoutList).hasSize(databaseSizeBeforeUpdate);
        DashboardLayout testDashboardLayout = dashboardLayoutList.get(dashboardLayoutList.size() - 1);
        assertThat(testDashboardLayout.getMinCols()).isEqualTo(UPDATED_MIN_COLS);
        assertThat(testDashboardLayout.getMinRows()).isEqualTo(UPDATED_MIN_ROWS);
        assertThat(testDashboardLayout.getMaxRows()).isEqualTo(UPDATED_MAX_ROWS);
    }

    @Test
    void putNonExistingDashboardLayout() throws Exception {
        int databaseSizeBeforeUpdate = dashboardLayoutRepository.findAll().size();
        dashboardLayout.setId(UUID.randomUUID().toString());

        // Create the DashboardLayout
        DashboardLayoutDTO dashboardLayoutDTO = dashboardLayoutMapper.toDto(dashboardLayout);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDashboardLayoutMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dashboardLayoutDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dashboardLayoutDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DashboardLayout in the database
        List<DashboardLayout> dashboardLayoutList = dashboardLayoutRepository.findAll();
        assertThat(dashboardLayoutList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchDashboardLayout() throws Exception {
        int databaseSizeBeforeUpdate = dashboardLayoutRepository.findAll().size();
        dashboardLayout.setId(UUID.randomUUID().toString());

        // Create the DashboardLayout
        DashboardLayoutDTO dashboardLayoutDTO = dashboardLayoutMapper.toDto(dashboardLayout);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDashboardLayoutMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dashboardLayoutDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DashboardLayout in the database
        List<DashboardLayout> dashboardLayoutList = dashboardLayoutRepository.findAll();
        assertThat(dashboardLayoutList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamDashboardLayout() throws Exception {
        int databaseSizeBeforeUpdate = dashboardLayoutRepository.findAll().size();
        dashboardLayout.setId(UUID.randomUUID().toString());

        // Create the DashboardLayout
        DashboardLayoutDTO dashboardLayoutDTO = dashboardLayoutMapper.toDto(dashboardLayout);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDashboardLayoutMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dashboardLayoutDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DashboardLayout in the database
        List<DashboardLayout> dashboardLayoutList = dashboardLayoutRepository.findAll();
        assertThat(dashboardLayoutList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateDashboardLayoutWithPatch() throws Exception {
        // Initialize the database
        dashboardLayout.setId(UUID.randomUUID().toString());
        dashboardLayoutRepository.save(dashboardLayout);

        int databaseSizeBeforeUpdate = dashboardLayoutRepository.findAll().size();

        // Update the dashboardLayout using partial update
        DashboardLayout partialUpdatedDashboardLayout = new DashboardLayout();
        partialUpdatedDashboardLayout.setId(dashboardLayout.getId());

        partialUpdatedDashboardLayout.minCols(UPDATED_MIN_COLS).minRows(UPDATED_MIN_ROWS);

        restDashboardLayoutMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDashboardLayout.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDashboardLayout))
            )
            .andExpect(status().isOk());

        // Validate the DashboardLayout in the database
        List<DashboardLayout> dashboardLayoutList = dashboardLayoutRepository.findAll();
        assertThat(dashboardLayoutList).hasSize(databaseSizeBeforeUpdate);
        DashboardLayout testDashboardLayout = dashboardLayoutList.get(dashboardLayoutList.size() - 1);
        assertThat(testDashboardLayout.getMinCols()).isEqualTo(UPDATED_MIN_COLS);
        assertThat(testDashboardLayout.getMinRows()).isEqualTo(UPDATED_MIN_ROWS);
        assertThat(testDashboardLayout.getMaxRows()).isEqualTo(DEFAULT_MAX_ROWS);
    }

    @Test
    void fullUpdateDashboardLayoutWithPatch() throws Exception {
        // Initialize the database
        dashboardLayout.setId(UUID.randomUUID().toString());
        dashboardLayoutRepository.save(dashboardLayout);

        int databaseSizeBeforeUpdate = dashboardLayoutRepository.findAll().size();

        // Update the dashboardLayout using partial update
        DashboardLayout partialUpdatedDashboardLayout = new DashboardLayout();
        partialUpdatedDashboardLayout.setId(dashboardLayout.getId());

        partialUpdatedDashboardLayout.minCols(UPDATED_MIN_COLS).minRows(UPDATED_MIN_ROWS).maxRows(UPDATED_MAX_ROWS);

        restDashboardLayoutMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDashboardLayout.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDashboardLayout))
            )
            .andExpect(status().isOk());

        // Validate the DashboardLayout in the database
        List<DashboardLayout> dashboardLayoutList = dashboardLayoutRepository.findAll();
        assertThat(dashboardLayoutList).hasSize(databaseSizeBeforeUpdate);
        DashboardLayout testDashboardLayout = dashboardLayoutList.get(dashboardLayoutList.size() - 1);
        assertThat(testDashboardLayout.getMinCols()).isEqualTo(UPDATED_MIN_COLS);
        assertThat(testDashboardLayout.getMinRows()).isEqualTo(UPDATED_MIN_ROWS);
        assertThat(testDashboardLayout.getMaxRows()).isEqualTo(UPDATED_MAX_ROWS);
    }

    @Test
    void patchNonExistingDashboardLayout() throws Exception {
        int databaseSizeBeforeUpdate = dashboardLayoutRepository.findAll().size();
        dashboardLayout.setId(UUID.randomUUID().toString());

        // Create the DashboardLayout
        DashboardLayoutDTO dashboardLayoutDTO = dashboardLayoutMapper.toDto(dashboardLayout);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDashboardLayoutMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dashboardLayoutDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dashboardLayoutDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DashboardLayout in the database
        List<DashboardLayout> dashboardLayoutList = dashboardLayoutRepository.findAll();
        assertThat(dashboardLayoutList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchDashboardLayout() throws Exception {
        int databaseSizeBeforeUpdate = dashboardLayoutRepository.findAll().size();
        dashboardLayout.setId(UUID.randomUUID().toString());

        // Create the DashboardLayout
        DashboardLayoutDTO dashboardLayoutDTO = dashboardLayoutMapper.toDto(dashboardLayout);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDashboardLayoutMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dashboardLayoutDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DashboardLayout in the database
        List<DashboardLayout> dashboardLayoutList = dashboardLayoutRepository.findAll();
        assertThat(dashboardLayoutList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamDashboardLayout() throws Exception {
        int databaseSizeBeforeUpdate = dashboardLayoutRepository.findAll().size();
        dashboardLayout.setId(UUID.randomUUID().toString());

        // Create the DashboardLayout
        DashboardLayoutDTO dashboardLayoutDTO = dashboardLayoutMapper.toDto(dashboardLayout);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDashboardLayoutMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dashboardLayoutDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DashboardLayout in the database
        List<DashboardLayout> dashboardLayoutList = dashboardLayoutRepository.findAll();
        assertThat(dashboardLayoutList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteDashboardLayout() throws Exception {
        // Initialize the database
        dashboardLayout.setId(UUID.randomUUID().toString());
        dashboardLayoutRepository.save(dashboardLayout);

        int databaseSizeBeforeDelete = dashboardLayoutRepository.findAll().size();

        // Delete the dashboardLayout
        restDashboardLayoutMockMvc
            .perform(delete(ENTITY_API_URL_ID, dashboardLayout.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DashboardLayout> dashboardLayoutList = dashboardLayoutRepository.findAll();
        assertThat(dashboardLayoutList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
