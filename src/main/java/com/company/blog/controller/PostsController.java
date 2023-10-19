package com.company.blog.controller;

import com.company.blog.dto.post.PostDetailsDto;
import com.company.blog.dto.post.PostDto;
import com.company.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/home")
public class PostsController {
    private PostService postService;

    @Autowired
    public PostsController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<PostDto> getPosts(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize
    ){
        return postService.getAllPosts(pageNumber, pageSize);
    }

    @GetMapping("/posts/{postSlug}")
    public PostDetailsDto getPostBySlug(@PathVariable(value = "postSlug") String postSlug) {
        return postService.getPost(postSlug);
    }

    @GetMapping("/posts/category/{categorySlug}")
    public List<PostDto> getPostsByCategory(@PathVariable(value = "categorySlug")String categorySlug) {
        return postService.findByCategory(categorySlug);
    }

    @GetMapping("/posts/search")
    public List<PostDto> searchPosts(@RequestParam(value = "title") String title) {
        return postService.searchPost(title);
    }
}
