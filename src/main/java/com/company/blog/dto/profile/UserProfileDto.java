package com.company.blog.dto.profile;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {

    private Long id;

    @Length(min = 3, max = 30)
    private String firstName;

    @Length(min = 3, max = 30)
    private String lastName;

    @Length(min = 3, max = 30)
    private String country;

    @Length(min = 3, max = 30)
    private String city;

}
