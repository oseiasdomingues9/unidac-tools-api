package com.unidac.tools.mapper;

import com.unidac.tools.dto.UserDTO;
import com.unidac.tools.entities.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(User user);

    List<UserDTO> toUserListDTO(List<User> userList);
}
