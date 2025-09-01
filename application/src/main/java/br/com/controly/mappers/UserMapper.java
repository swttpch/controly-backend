package br.com.controly.mappers;

import br.com.controly.dtos.SimplifiedUserResponse;
import br.com.controly.dtos.UpdateUsersInfoRequest;
import br.com.controly.domain.entities.UserEntity;
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
