package com.vicheak.cms.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Content {

    private Integer id;
    private String uuid;
    private String slug;
    private String title;
    private String description;
    private String thumbnail;
    private String keyword;
    private String editor;
    private Boolean isDeleted;
    private LocalDateTime createdAt;
    private Category category;

}
