package com.dev.devops.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDTO {

    private String id;
    private String name;
    private String section;
    private String department;
    private int year;
    private String joinedAt;

}
