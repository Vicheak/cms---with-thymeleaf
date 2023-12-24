package com.vicheak.cms.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Content {

    private Integer id;
    private String uuid;
    private String slug;
    @NotBlank(message = "Content title must not be blank!")
    private String title;
    @NotBlank(message = "Content description must not be blank!")
    private String description;
    private String thumbnail;
    @NotBlank(message = "Content keyword must not be blank!")
    private String keyword;
    private String editor;
    private Boolean isDeleted;
    private LocalDateTime createdAt;
    @NotNull(message = "Please choose a category!")
    private Category category;

}
