package com.vicheak.cms.model;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Integer id;
    private String uuid;
    private String username;
    private String password;
    private String displayName;
    private Boolean isDeleted;
    private LocalDate createdAt;
    private List<Role> roles;

}
