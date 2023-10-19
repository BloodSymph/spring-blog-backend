package com.company.blog.mapper;


import com.company.blog.domain.PostEntity;
import com.company.blog.dto.post.PostDetailsDto;
import com.company.blog.dto.post.PostDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static com.company.blog.mapper.CategoryMapper.mapToCategoryDto;
import static com.company.blog.mapper.CommentMapper.mapToCommentDto;


@Component
public class PostMapper {
    public static PostDto mapToPostDto(PostEntity post) {
        return PostDto.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .slug(post.getSlug())
                    .created(post.getCreated())
                    .updated(post.getUpdated())
                .build();
    }

    public static PostDetailsDto mapToPostDetailsDto(PostEntity post) {
        return PostDetailsDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .slug(post.getSlug())
                .created(post.getCreated())
                .updated(post.getUpdated())
                .category(mapToCategoryDto(post.getCategory()))
                .comments(post.getComments().stream().map(comment -> mapToCommentDto(comment)).collect(Collectors.toList()))
                .build();
    }
    public static PostEntity mapToPost(PostDto postDto) {
        return PostEntity.builder()
                    .id(postDto.getId())
                    .title(postDto.getTitle())
                    .slug(postDto.getSlug())
                    .created(postDto.getCreated())
                    .updated(postDto.getUpdated())
                .build();
    }
}
