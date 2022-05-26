package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.DashboardItem;
import com.mycompany.myapp.domain.DashboardLayout;
import com.mycompany.myapp.domain.UserProfile;
import com.mycompany.myapp.service.dto.DashboardItemDTO;
import com.mycompany.myapp.service.dto.DashboardLayoutDTO;
import com.mycompany.myapp.service.dto.UserProfileDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserProfile} and its DTO {@link UserProfileDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserProfileMapper extends EntityMapper<UserProfileDTO, UserProfile> {
    @Mapping(target = "dashboardLayout", source = "dashboardLayout", qualifiedByName = "dashboardLayoutId")
    @Mapping(target = "dashboardItem", source = "dashboardItem", qualifiedByName = "dashboardItemId")
    UserProfileDTO toDto(UserProfile s);

    @Named("dashboardLayoutId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DashboardLayoutDTO toDtoDashboardLayoutId(DashboardLayout dashboardLayout);

    @Named("dashboardItemId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DashboardItemDTO toDtoDashboardItemId(DashboardItem dashboardItem);
}
