package com.productservice.userservice.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.productservice.userservice.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@JsonDeserialize(as = CustomGrantedAuthority.class)
@NoArgsConstructor
public class CustomGrantedAuthority implements GrantedAuthority {
    private Role role;

    public CustomGrantedAuthority(Role role) {
        this.role = role;
    }

    @Override
    @JsonIgnore
    public String getAuthority() {
        return role.getRole();
    }
}
