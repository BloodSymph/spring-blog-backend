package com.company.blog.service;

import com.company.blog.dto.comment.CommentDetailsDto;
import com.company.blog.dto.comment.CommentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    List<CommentDto> getAllComments(int pageNumber, int pageSize);
    CommentDetailsDto getComment(String commentSlug);
    List<CommentDto> searchComment(String message);
    List<CommentDto> findByPost(String postSlug);
    CommentDto createComment(CommentDto commentDto, String postSlug);
    void deleteComment(String commentSlug);
}
