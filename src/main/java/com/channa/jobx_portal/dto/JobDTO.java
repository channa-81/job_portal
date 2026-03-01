package com.channa.jobx_portal.dto;

import lombok.Data;

@Data
public class JobDTO {
    private Long id;
    private String title;
    private String description;
    private String location;
    private Long companyId;
}