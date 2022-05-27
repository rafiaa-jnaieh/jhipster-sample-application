package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.DashboardConfigRepository;
import com.mycompany.myapp.service.DashboardConfigService;
import com.mycompany.myapp.service.dto.DashboardConfigDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.DashboardConfig}.
 */
@RestController
@RequestMapping("/api")
public class DashboardConfigResource {

    private final Logger log = LoggerFactory.getLogger(DashboardConfigResource.class);

    private static final String ENTITY_NAME = "dashboardConfig";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DashboardConfigService dashboardConfigService;

    private final DashboardConfigRepository dashboardConfigRepository;

    public DashboardConfigResource(DashboardConfigService dashboardConfigService, DashboardConfigRepository dashboardConfigRepository) {
        this.dashboardConfigService = dashboardConfigService;
        this.dashboardConfigRepository = dashboardConfigRepository;
    }

    /**
     * {@code POST  /dashboard-configs} : Create a new dashboardConfig.
     *
     * @param dashboardConfigDTO the dashboardConfigDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dashboardConfigDTO, or with status {@code 400 (Bad Request)} if the dashboardConfig has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dashboard-configs")
    public ResponseEntity<DashboardConfigDTO> createDashboardConfig(@RequestBody DashboardConfigDTO dashboardConfigDTO)
        throws URISyntaxException {
        log.debug("REST request to save DashboardConfig : {}", dashboardConfigDTO);
        if (dashboardConfigDTO.getId() != null) {
            throw new BadRequestAlertException("A new dashboardConfig cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DashboardConfigDTO result = dashboardConfigService.save(dashboardConfigDTO);
        return ResponseEntity
            .created(new URI("/api/dashboard-configs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /dashboard-configs/:id} : Updates an existing dashboardConfig.
     *
     * @param id the id of the dashboardConfigDTO to save.
     * @param dashboardConfigDTO the dashboardConfigDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dashboardConfigDTO,
     * or with status {@code 400 (Bad Request)} if the dashboardConfigDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dashboardConfigDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dashboard-configs/{id}")
    public ResponseEntity<DashboardConfigDTO> updateDashboardConfig(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DashboardConfigDTO dashboardConfigDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DashboardConfig : {}, {}", id, dashboardConfigDTO);
        if (dashboardConfigDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dashboardConfigDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dashboardConfigRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DashboardConfigDTO result = dashboardConfigService.update(dashboardConfigDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dashboardConfigDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /dashboard-configs/:id} : Partial updates given fields of an existing dashboardConfig, field will ignore if it is null
     *
     * @param id the id of the dashboardConfigDTO to save.
     * @param dashboardConfigDTO the dashboardConfigDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dashboardConfigDTO,
     * or with status {@code 400 (Bad Request)} if the dashboardConfigDTO is not valid,
     * or with status {@code 404 (Not Found)} if the dashboardConfigDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the dashboardConfigDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/dashboard-configs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DashboardConfigDTO> partialUpdateDashboardConfig(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DashboardConfigDTO dashboardConfigDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DashboardConfig partially : {}, {}", id, dashboardConfigDTO);
        if (dashboardConfigDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dashboardConfigDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dashboardConfigRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DashboardConfigDTO> result = dashboardConfigService.partialUpdate(dashboardConfigDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dashboardConfigDTO.getId())
        );
    }

    /**
     * {@code GET  /dashboard-configs} : get all the dashboardConfigs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dashboardConfigs in body.
     */
    @GetMapping("/dashboard-configs")
    public List<DashboardConfigDTO> getAllDashboardConfigs() {
        log.debug("REST request to get all DashboardConfigs");
        return dashboardConfigService.findAll();
    }

    /**
     * {@code GET  /dashboard-configs/:id} : get the "id" dashboardConfig.
     *
     * @param id the id of the dashboardConfigDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dashboardConfigDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dashboard-configs/{id}")
    public ResponseEntity<DashboardConfigDTO> getDashboardConfig(@PathVariable String id) {
        log.debug("REST request to get DashboardConfig : {}", id);
        Optional<DashboardConfigDTO> dashboardConfigDTO = dashboardConfigService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dashboardConfigDTO);
    }

    /**
     * {@code DELETE  /dashboard-configs/:id} : delete the "id" dashboardConfig.
     *
     * @param id the id of the dashboardConfigDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dashboard-configs/{id}")
    public ResponseEntity<Void> deleteDashboardConfig(@PathVariable String id) {
        log.debug("REST request to delete DashboardConfig : {}", id);
        dashboardConfigService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
