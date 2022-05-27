package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.DashboardLayout;
import com.mycompany.myapp.repository.DashboardLayoutRepository;
import com.mycompany.myapp.service.DashboardLayoutService;
import com.mycompany.myapp.service.dto.DashboardLayoutDTO;
import com.mycompany.myapp.service.mapper.DashboardLayoutMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link DashboardLayout}.
 */
@Service
public class DashboardLayoutServiceImpl implements DashboardLayoutService {

    private final Logger log = LoggerFactory.getLogger(DashboardLayoutServiceImpl.class);

    private final DashboardLayoutRepository dashboardLayoutRepository;

    private final DashboardLayoutMapper dashboardLayoutMapper;

    public DashboardLayoutServiceImpl(DashboardLayoutRepository dashboardLayoutRepository, DashboardLayoutMapper dashboardLayoutMapper) {
        this.dashboardLayoutRepository = dashboardLayoutRepository;
        this.dashboardLayoutMapper = dashboardLayoutMapper;
    }

    @Override
    public DashboardLayoutDTO save(DashboardLayoutDTO dashboardLayoutDTO) {
        log.debug("Request to save DashboardLayout : {}", dashboardLayoutDTO);
        DashboardLayout dashboardLayout = dashboardLayoutMapper.toEntity(dashboardLayoutDTO);
        dashboardLayout = dashboardLayoutRepository.save(dashboardLayout);
        return dashboardLayoutMapper.toDto(dashboardLayout);
    }

    @Override
    public DashboardLayoutDTO update(DashboardLayoutDTO dashboardLayoutDTO) {
        log.debug("Request to save DashboardLayout : {}", dashboardLayoutDTO);
        DashboardLayout dashboardLayout = dashboardLayoutMapper.toEntity(dashboardLayoutDTO);
        dashboardLayout.setIsPersisted();
        dashboardLayout = dashboardLayoutRepository.save(dashboardLayout);
        return dashboardLayoutMapper.toDto(dashboardLayout);
    }

    @Override
    public Optional<DashboardLayoutDTO> partialUpdate(DashboardLayoutDTO dashboardLayoutDTO) {
        log.debug("Request to partially update DashboardLayout : {}", dashboardLayoutDTO);

        return dashboardLayoutRepository
            .findById(dashboardLayoutDTO.getId())
            .map(existingDashboardLayout -> {
                dashboardLayoutMapper.partialUpdate(existingDashboardLayout, dashboardLayoutDTO);

                return existingDashboardLayout;
            })
            .map(dashboardLayoutRepository::save)
            .map(dashboardLayoutMapper::toDto);
    }

    @Override
    public List<DashboardLayoutDTO> findAll() {
        log.debug("Request to get all DashboardLayouts");
        return dashboardLayoutRepository
            .findAll()
            .stream()
            .map(dashboardLayoutMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public Optional<DashboardLayoutDTO> findOne(String id) {
        log.debug("Request to get DashboardLayout : {}", id);
        return dashboardLayoutRepository.findById(id).map(dashboardLayoutMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete DashboardLayout : {}", id);
        dashboardLayoutRepository.deleteById(id);
    }
}
