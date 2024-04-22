package az.uni.unitech.mapper;

import az.uni.unitech.dto.request.UserRegistration;
import az.uni.unitech.dto.response.UserResponse;
import az.uni.unitech.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toDomain(UserRegistration registration);

    UserResponse toDto(User user);

}