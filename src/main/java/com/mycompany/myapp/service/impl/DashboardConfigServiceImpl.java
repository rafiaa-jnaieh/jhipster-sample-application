package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.DashboardConfig;
import com.mycompany.myapp.repository.DashboardConfigRepository;
import com.mycompany.myapp.service.DashboardConfigService;
import com.mycompany.myapp.service.dto.DashboardConfigDTO;
import com.mycompany.myapp.service.mapper.DashboardConfigMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link DashboardConfig}.
 */
@Service
public class DashboardConfigServiceImpl implements DashboardConfigService {

    private final Logger log = LoggerFactory.getLogger(DashboardConfigServiceImpl.class);

    private final DashboardConfigRepository dashboardConfigRepository;

    private final DashboardConfigMapper dashboardConfigMapper;

    public DashboardConfigServiceImpl(DashboardConfigRepository dashboardConfigRepository, DashboardConfigMapper dashboardConfigMapper) {
        this.dashboardConfigRepository = dashboardConfigRepository;
        this.dashboardConfigMapper = dashboardConfigMapper;
    }

    @Override
    public DashboardConfigDTO save(DashboardConfigDTO dashboardConfigDTO) {
        log.debug("Request to save DashboardConfig : {}", dashboardConfigDTO);
        DashboardConfig dashboardConfig = dashboardConfigMapper.toEntity(dashboardConfigDTO);
        dashboardConfig = dashboardConfigRepository.save(dashboardConfig);
        return dashboardConfigMapper.toDto(dashboardConfig);
    }

    @Override
    public DashboardConfigDTO update(DashboardConfigDTO dashboardConfigDTO) {
        log.debug("Request to save DashboardConfig : {}", dashboardConfigDTO);
        DashboardConfig dashboardConfig = dashboardConfigMapper.toEntity(dashboardConfigDTO);
        dashboardConfig.setIsPersisted();
        dashboardConfig = dashboardConfigRepository.save(dashboardConfig);
        return dashboardConfigMapper.toDto(dashboardConfig);
    }

    @Override
    public Optional<DashboardConfigDTO> partialUpdate(DashboardConfigDTO dashboardConfigDTO) {
        log.debug("Request to partially update DashboardConfig : {}", dashboardConfigDTO);

        return dashboardConfigRepository
            .findById(dashboardConfigDTO.getId())
            .map(existingDashboardConfig -> {
                dashboardConfigMapper.partialUpdate(existingDashboardConfig, dashboardConfigDTO);

                return existingDashboardConfig;
            })
            .map(dashboardConfigRepository::save)
            .map(dashboardConfigMapper::toDto);
    }

    @Override
    public List<DashboardConfigDTO> findAll() {
        log.debug("Request to get all DashboardConfigs");
        return dashboardConfigRepository
            .findAll()
            .stream()
            .map(dashboardConfigMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public Optional<DashboardConfigDTO> findOne(String id) {
        log.debug("Request to get DashboardConfig : {}", id);
        return dashboardConfigRepository.findById(id).map(dashboardConfigMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete DashboardConfig : {}", id);
        dashboardConfigRepository.deleteById(id);
    }
}
