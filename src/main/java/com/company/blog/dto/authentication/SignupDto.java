package com.company.blog.dto.authentication;

import com.company.blog.validator.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupDto {

    @NotEmpty
    @NotBlank(message = "Username should not be empty")
    @Length(min = 3)
    private String username;

    @Email
    @NotEmpty
    @NotBlank(message = "Email should not be empty")
    private String email;

    @NotEmpty
    @NotBlank(message = "Password should not be empty")
    @ValidPassword
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime created;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updated;

}
