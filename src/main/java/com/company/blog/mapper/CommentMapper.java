package com.company.blog.mapper;

import com.company.blog.domain.CommentEntity;
import com.company.blog.dto.comment.CommentDetailsDto;
import com.company.blog.dto.comment.CommentDto;
import org.springframework.stereotype.Component;

import static com.company.blog.mapper.PostMapper.mapToPostDto;
import static com.company.blog.mapper.UserMapper.mapToUserDto;

@Component
public class CommentMapper {
    public static CommentDto mapToCommentDto(CommentEntity comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .slug(comment.getSlug())
                .message(comment.getMessage())
                .build();
    }

    public static CommentDetailsDto mapToCommentDetailsDto(CommentEntity comment) {
        return CommentDetailsDto.builder()
                .id(comment.getId())
                .message(comment.getMessage())
                .slug(comment.getSlug())
                .created(comment.getCreated())
                .updated(comment.getUpdated())
                .post(mapToPostDto(comment.getPost()))
                .user(mapToUserDto(comment.getUser()))
                .build();
    }

    public static CommentEntity mapToComment(CommentDto commentDto) {
        return CommentEntity.builder()
                .id(commentDto.getId())
                .slug(commentDto.getSlug())
                .message(commentDto.getMessage())
                .build();
    }
}
