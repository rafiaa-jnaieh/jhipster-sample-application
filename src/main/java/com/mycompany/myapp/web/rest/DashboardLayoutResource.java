package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.DashboardLayoutRepository;
import com.mycompany.myapp.service.DashboardLayoutService;
import com.mycompany.myapp.service.dto.DashboardLayoutDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.DashboardLayout}.
 */
@RestController
@RequestMapping("/api")
public class DashboardLayoutResource {

    private final Logger log = LoggerFactory.getLogger(DashboardLayoutResource.class);

    private static final String ENTITY_NAME = "dashboardLayout";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DashboardLayoutService dashboardLayoutService;

    private final DashboardLayoutRepository dashboardLayoutRepository;

    public DashboardLayoutResource(DashboardLayoutService dashboardLayoutService, DashboardLayoutRepository dashboardLayoutRepository) {
        this.dashboardLayoutService = dashboardLayoutService;
        this.dashboardLayoutRepository = dashboardLayoutRepository;
    }

    /**
     * {@code POST  /dashboard-layouts} : Create a new dashboardLayout.
     *
     * @param dashboardLayoutDTO the dashboardLayoutDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dashboardLayoutDTO, or with status {@code 400 (Bad Request)} if the dashboardLayout has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dashboard-layouts")
    public ResponseEntity<DashboardLayoutDTO> createDashboardLayout(@RequestBody DashboardLayoutDTO dashboardLayoutDTO)
        throws URISyntaxException {
        log.debug("REST request to save DashboardLayout : {}", dashboardLayoutDTO);
        if (dashboardLayoutDTO.getId() != null) {
            throw new BadRequestAlertException("A new dashboardLayout cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DashboardLayoutDTO result = dashboardLayoutService.save(dashboardLayoutDTO);
        return ResponseEntity
            .created(new URI("/api/dashboard-layouts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /dashboard-layouts/:id} : Updates an existing dashboardLayout.
     *
     * @param id the id of the dashboardLayoutDTO to save.
     * @param dashboardLayoutDTO the dashboardLayoutDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dashboardLayoutDTO,
     * or with status {@code 400 (Bad Request)} if the dashboardLayoutDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dashboardLayoutDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dashboard-layouts/{id}")
    public ResponseEntity<DashboardLayoutDTO> updateDashboardLayout(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DashboardLayoutDTO dashboardLayoutDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DashboardLayout : {}, {}", id, dashboardLayoutDTO);
        if (dashboardLayoutDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dashboardLayoutDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dashboardLayoutRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DashboardLayoutDTO result = dashboardLayoutService.update(dashboardLayoutDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dashboardLayoutDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /dashboard-layouts/:id} : Partial updates given fields of an existing dashboardLayout, field will ignore if it is null
     *
     * @param id the id of the dashboardLayoutDTO to save.
     * @param dashboardLayoutDTO the dashboardLayoutDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dashboardLayoutDTO,
     * or with status {@code 400 (Bad Request)} if the dashboardLayoutDTO is not valid,
     * or with status {@code 404 (Not Found)} if the dashboardLayoutDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the dashboardLayoutDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/dashboard-layouts/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DashboardLayoutDTO> partialUpdateDashboardLayout(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DashboardLayoutDTO dashboardLayoutDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DashboardLayout partially : {}, {}", id, dashboardLayoutDTO);
        if (dashboardLayoutDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dashboardLayoutDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dashboardLayoutRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DashboardLayoutDTO> result = dashboardLayoutService.partialUpdate(dashboardLayoutDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dashboardLayoutDTO.getId())
        );
    }

    /**
     * {@code GET  /dashboard-layouts} : get all the dashboardLayouts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dashboardLayouts in body.
     */
    @GetMapping("/dashboard-layouts")
    public List<DashboardLayoutDTO> getAllDashboardLayouts() {
        log.debug("REST request to get all DashboardLayouts");
        return dashboardLayoutService.findAll();
    }

    /**
     * {@code GET  /dashboard-layouts/:id} : get the "id" dashboardLayout.
     *
     * @param id the id of the dashboardLayoutDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dashboardLayoutDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dashboard-layouts/{id}")
    public ResponseEntity<DashboardLayoutDTO> getDashboardLayout(@PathVariable String id) {
        log.debug("REST request to get DashboardLayout : {}", id);
        Optional<DashboardLayoutDTO> dashboardLayoutDTO = dashboardLayoutService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dashboardLayoutDTO);
    }

    /**
     * {@code DELETE  /dashboard-layouts/:id} : delete the "id" dashboardLayout.
     *
     * @param id the id of the dashboardLayoutDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dashboard-layouts/{id}")
    public ResponseEntity<Void> deleteDashboardLayout(@PathVariable String id) {
        log.debug("REST request to delete DashboardLayout : {}", id);
        dashboardLayoutService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
