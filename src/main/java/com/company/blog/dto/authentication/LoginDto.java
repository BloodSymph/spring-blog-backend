package com.company.blog.dto.authentication;

import com.company.blog.validator.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    @NotEmpty
    @NotBlank(message = "Username should not be empty")
    @Length(min = 3)
    private String username;

    @NotEmpty
    @NotBlank(message = "Password should not be empty")
    @ValidPassword
    private String password;

}
