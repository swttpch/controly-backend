package codelabz.mapper;

import codelabz.dto.SimplifiedUserResponse;
import codelabz.dto.UpdateUsersInfoRequest;
import codelabz.entity.UserEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UpdateUsersInfoRequest dto, @MappingTarget UserEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void getDtoFromUser(UserEntity entity, @MappingTarget SimplifiedUserResponse dto);
}
