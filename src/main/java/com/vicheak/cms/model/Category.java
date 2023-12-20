package com.vicheak.cms.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    private Integer id;
    private String name;
    private Boolean isDeleted;

}
