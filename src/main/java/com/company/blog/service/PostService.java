package com.company.blog.service;

import com.company.blog.domain.PostEntity;
import com.company.blog.dto.post.PostDetailsDto;
import com.company.blog.dto.post.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    List<PostDto> getAllPosts(int pageNumber, int pageSize);
    List<PostDto> searchPost(String title);
    List<PostDto> findByCategory(String categorySlug);
    PostDetailsDto getPost(String postSlug);
    PostDto createPost(PostDto postDto, String categorySlug);
    PostDto updatePost(PostDetailsDto postDto, String postSlug);
    void deletePost(String postSlug);
}
