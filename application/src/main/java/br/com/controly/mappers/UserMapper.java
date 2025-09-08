package br.com.controly.mappers;

import br.com.controly.dtos.NewUserDTO;
import br.com.controly.viewmodels.UserViewModel;
import br.com.controly.dtos.UserDTO;
import br.com.controly.domain.entities.Users;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    Users toUserEntity(NewUserDTO createNewUserDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toUserEntity(UserDTO dto, @MappingTarget Users entity);

    UserViewModel toUserViewModel(Users entity);


}
