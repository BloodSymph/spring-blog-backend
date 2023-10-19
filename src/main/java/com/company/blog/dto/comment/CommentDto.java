package com.company.blog.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long id;

    @NotEmpty
    @NotBlank(message = "Slug field should not be empty")
    @Length(min = 3, max = 20)
    private String slug;

    @NotEmpty
    @NotBlank(message = "Comment message field should not be empty!")
    @Length(min = 10, max = 5000)
    private String message;

}
