package com.company.blog.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
public class CategoryDto {
    private Long id;

    @NotEmpty
    @NotBlank(message = "Category name not should be empty")
    @Length(min = 3, max=40)
    private String name;

    @NotEmpty
    @NotBlank(message = "Slug field should not be empty")
    @Length(min = 3, max = 20)
    private String slug;
}
