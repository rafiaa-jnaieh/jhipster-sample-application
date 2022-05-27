package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.DashboardConfig;
import com.mycompany.myapp.service.dto.DashboardConfigDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DashboardConfig} and its DTO {@link DashboardConfigDTO}.
 */
@Mapper(componentModel = "spring")
public interface DashboardConfigMapper extends EntityMapper<DashboardConfigDTO, DashboardConfig> {}
