package com.channa.jobx_portal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Candidate Data Transfer Object")
public class CandidateDTO {

	@Schema(description = "Candidate ID", example = "1")
    private Long id;

	 @Schema(description = "Candidate full name", example = "Ravi Kumar")
    @NotBlank(message = "Name is required")
    private String name;

	 @Schema(description = "Email address", example = "ravi@gmail.com")
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

	 
	 @Schema(description = "Skills of candidate", example = "Java, Spring Boot")
    @NotBlank(message = "Skills are required")
    private String skills;
}