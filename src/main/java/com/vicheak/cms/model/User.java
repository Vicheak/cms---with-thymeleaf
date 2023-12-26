package com.vicheak.cms.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {

    private Integer id;
    private String uuid;
    @NotBlank(message = "Username must not be blank!")
    private String username;
    @NotBlank(message = "Password must not be blank!")
    private String password;
    @NotBlank(message = "Password must not be blank!")
    private String displayName;
    private Boolean isDeleted;
    private LocalDate createdAt;
    @NotNull(message = "Please select at least one role!")
    @Size(min = 1, message = "Please select at least one role!")
    private List<@NotNull(message = "Role must not be null!") Role> roles;

}
