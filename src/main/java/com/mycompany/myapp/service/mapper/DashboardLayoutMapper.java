package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.DashboardLayout;
import com.mycompany.myapp.service.dto.DashboardLayoutDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DashboardLayout} and its DTO {@link DashboardLayoutDTO}.
 */
@Mapper(componentModel = "spring")
public interface DashboardLayoutMapper extends EntityMapper<DashboardLayoutDTO, DashboardLayout> {}
