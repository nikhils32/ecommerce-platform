package com.productservice.userservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.productservice.userservice.model.Role;
import com.productservice.userservice.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDto {
    private String email;
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

    public static UserDto from(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());

        return userDto;
    }
}
