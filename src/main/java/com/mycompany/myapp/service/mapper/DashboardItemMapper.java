package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.DashboardItem;
import com.mycompany.myapp.domain.UserProfile;
import com.mycompany.myapp.service.dto.DashboardItemDTO;
import com.mycompany.myapp.service.dto.UserProfileDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DashboardItem} and its DTO {@link DashboardItemDTO}.
 */
@Mapper(componentModel = "spring")
public interface DashboardItemMapper extends EntityMapper<DashboardItemDTO, DashboardItem> {
    @Mapping(target = "userProfile", source = "userProfile", qualifiedByName = "userProfileId")
    DashboardItemDTO toDto(DashboardItem s);

    @Named("userProfileId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserProfileDTO toDtoUserProfileId(UserProfile userProfile);
}
