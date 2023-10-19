package com.company.blog.controller;

import com.company.blog.dto.comment.CommentDetailsDto;
import com.company.blog.dto.comment.CommentDto;
import com.company.blog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/home")
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/posts/{postSlug}/comments")
    public List<CommentDto> getCommentForPost(@PathVariable(value = "postSlug") String postSlug) {
        return commentService.findByPost(postSlug);
    }

    @PostMapping("/posts/{postSlug}/comments/new")
    public CommentDto createNewComment(
            @PathVariable(value = "postSlug") String postSlug,
            @Valid @RequestBody CommentDto commentDto
    ) {
        return commentService.createComment(commentDto, postSlug);
    }
}
