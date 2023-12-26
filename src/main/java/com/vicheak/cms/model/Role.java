package com.vicheak.cms.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Role implements GrantedAuthority {

    private Integer id;
    private String name;

    @Override
    public String getAuthority() {
        //ex : ROLE_ADMIN, ...
        return "ROLE_" + name;
    }
}
