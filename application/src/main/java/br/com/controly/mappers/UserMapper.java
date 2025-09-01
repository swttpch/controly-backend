package br.com.controly.mappers;

import br.com.controly.viewmodels.SimplifiedUserViewModel;
import br.com.controly.dtos.UpdateUsersInfoDTO;
import br.com.controly.domain.entities.UserEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UpdateUsersInfoDTO dto, @MappingTarget UserEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void getDtoFromUser(UserEntity entity, @MappingTarget SimplifiedUserViewModel dto);
}
