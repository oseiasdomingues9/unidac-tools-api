package com.unidac.tools.dto;

import com.unidac.tools.entities.UserRole;

public record UserDTO(String id,String login, String password, UserRole role) {
}
