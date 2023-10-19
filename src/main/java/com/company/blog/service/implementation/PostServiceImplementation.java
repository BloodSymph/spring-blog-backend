package com.company.blog.service.implementation;

import com.company.blog.domain.CategoryEntity;
import com.company.blog.domain.PostEntity;
import com.company.blog.dto.post.PostDetailsDto;
import com.company.blog.dto.post.PostDto;
import com.company.blog.exception.CategoryNotFoundException;
import com.company.blog.exception.PostNotFoundException;
import com.company.blog.mapper.PostMapper;
import com.company.blog.repository.CategoryRepository;
import com.company.blog.repository.PostRepository;
import com.company.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.company.blog.utils.SlugGenerator;

import java.util.List;
import java.util.stream.Collectors;

import static com.company.blog.mapper.PostMapper.*;
import static com.company.blog.utils.SlugGenerator.toSlug;

@Service
public class PostServiceImplementation implements PostService {
    private PostRepository postRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public PostServiceImplementation(PostRepository postRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<PostDto> getAllPosts(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<PostEntity> posts = postRepository.findAll(pageable);
        List<PostEntity> listOfPosts = posts.getContent();
        return listOfPosts.stream().map(PostMapper::mapToPostDto).collect(Collectors.toList());
    }

    @Override
    public PostDetailsDto getPost(String postSlug) {
        PostEntity post = postRepository
                .findBySlug(postSlug)
                .orElseThrow(
                        () -> new PostNotFoundException("Can not found publication by slug: " + postSlug)
                );
        return mapToPostDetailsDto(post);
    }

    @Override
    public List<PostDto> findByCategory(String categorySlug) {
        List<PostEntity> posts = postRepository.findByCategorySlug(categorySlug);
        return posts.stream().map(PostMapper::mapToPostDto).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPost(String title) {
        List<PostEntity> posts = postRepository.searchByTitleIgnoreCase(title);
        return posts.stream().map(PostMapper::mapToPostDto).collect(Collectors.toList());
    }

    @Override
    public PostDto createPost(PostDto postDto, String categorySlug) {
        CategoryEntity category = categoryRepository
                .findBySlug(categorySlug)
                .orElseThrow(
                        () -> new CategoryNotFoundException(
                                "Category not found by slug: " + categorySlug
                        )
                );
        PostEntity post = mapToPost(postDto);
        post.setCategory(category);
        post.setSlug(toSlug(postDto.getTitle()));
        PostEntity createdPost = postRepository.save(post);
        return mapToPostDto(createdPost);
    }

    @Override
    public PostDto updatePost(PostDetailsDto postDetailsDto, String postSlug) {
        PostEntity post = postRepository
                .findBySlug(postSlug)
                .orElseThrow(
                        () -> new PostNotFoundException(
                                "Can not update publication with slug: " + postSlug
                        )
                );
        post.setTitle(postDetailsDto.getTitle());
        post.setSlug(postDetailsDto.getSlug());
        post.setCategory(post.getCategory());
        PostEntity updatedPost = postRepository.save(post);
        return mapToPostDto(updatedPost);
    }

    @Override
    public void deletePost(String postSlug) {
        if(!postRepository.existsBySlug(postSlug)) {
            throw new PostNotFoundException("Can not delete publication by current slug: " + postSlug);
        }
        postRepository.deleteBySlug(postSlug);
    }
}
