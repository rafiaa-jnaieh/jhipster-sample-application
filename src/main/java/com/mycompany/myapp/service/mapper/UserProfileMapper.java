package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.DashboardConfig;
import com.mycompany.myapp.domain.DashboardLayout;
import com.mycompany.myapp.domain.UserProfile;
import com.mycompany.myapp.service.dto.DashboardConfigDTO;
import com.mycompany.myapp.service.dto.DashboardLayoutDTO;
import com.mycompany.myapp.service.dto.UserProfileDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserProfile} and its DTO {@link UserProfileDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserProfileMapper extends EntityMapper<UserProfileDTO, UserProfile> {
    @Mapping(target = "dashboardLayout", source = "dashboardLayout", qualifiedByName = "dashboardLayoutId")
    @Mapping(target = "dashboardConfig", source = "dashboardConfig", qualifiedByName = "dashboardConfigId")
    UserProfileDTO toDto(UserProfile s);

    @Named("dashboardLayoutId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DashboardLayoutDTO toDtoDashboardLayoutId(DashboardLayout dashboardLayout);

    @Named("dashboardConfigId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DashboardConfigDTO toDtoDashboardConfigId(DashboardConfig dashboardConfig);
}
