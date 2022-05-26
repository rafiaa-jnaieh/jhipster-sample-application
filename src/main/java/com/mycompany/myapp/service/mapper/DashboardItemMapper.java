package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.DashboardItem;
import com.mycompany.myapp.service.dto.DashboardItemDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DashboardItem} and its DTO {@link DashboardItemDTO}.
 */
@Mapper(componentModel = "spring")
public interface DashboardItemMapper extends EntityMapper<DashboardItemDTO, DashboardItem> {}
