package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.UserProfile;
import com.mycompany.myapp.service.dto.UserProfileDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserProfile} and its DTO {@link UserProfileDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserProfileMapper extends EntityMapper<UserProfileDTO, UserProfile> {}
