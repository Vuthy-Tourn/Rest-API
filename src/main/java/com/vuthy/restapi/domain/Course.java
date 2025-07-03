package com.vuthy.restapi.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private String id;
    private String code;
    private String title;
    private Double price;
    private Boolean status;

}
