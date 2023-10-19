package com.company.blog.dto.post;

import com.company.blog.domain.CategoryEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;

    @NotEmpty
    @NotBlank(message = "Post title should not be empty")
    @Length(min = 3, max = 256)
    private String title;

    @NotEmpty
    @NotBlank(message = "Slug field should not be empty")
    @Length(min = 3, max = 20)
    private String slug;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime created;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updated;

}
