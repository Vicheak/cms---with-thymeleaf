package com.vicheak.cms.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Category {

    private Integer id;
    @NotBlank(message = "Category name must not be blank!")
    private String name;
    private Boolean isDeleted;

}
