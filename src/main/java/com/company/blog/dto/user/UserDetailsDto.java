package com.company.blog.dto.user;

import com.company.blog.dto.comment.CommentDto;
import com.company.blog.dto.profile.UserProfileDto;
import com.company.blog.dto.role.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDetailsDto extends UserDto{

    private List<CommentDto> comments;

    private UserProfileDto profile;

}
