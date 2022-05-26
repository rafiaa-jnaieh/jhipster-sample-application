package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.DashboardLayout;
import com.mycompany.myapp.domain.UserProfile;
import com.mycompany.myapp.service.dto.DashboardLayoutDTO;
import com.mycompany.myapp.service.dto.UserProfileDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DashboardLayout} and its DTO {@link DashboardLayoutDTO}.
 */
@Mapper(componentModel = "spring")
public interface DashboardLayoutMapper extends EntityMapper<DashboardLayoutDTO, DashboardLayout> {
    @Mapping(target = "userProfile", source = "userProfile", qualifiedByName = "userProfileId")
    DashboardLayoutDTO toDto(DashboardLayout s);

    @Named("userProfileId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserProfileDTO toDtoUserProfileId(UserProfile userProfile);
}
