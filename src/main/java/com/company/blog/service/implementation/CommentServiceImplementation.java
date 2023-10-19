package com.company.blog.service.implementation;

import com.company.blog.domain.CommentEntity;
import com.company.blog.domain.PostEntity;
import com.company.blog.domain.UserEntity;
import com.company.blog.dto.comment.CommentDetailsDto;
import com.company.blog.dto.comment.CommentDto;
import com.company.blog.exception.CategoryNotFoundException;
import com.company.blog.exception.CommentNotFoundException;
import com.company.blog.exception.PostNotFoundException;
import com.company.blog.mapper.CommentMapper;
import com.company.blog.repository.CommentRepository;
import com.company.blog.repository.PostRepository;
import com.company.blog.repository.UserRepository;
import com.company.blog.utils.SecurityUtil;
import com.company.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.company.blog.mapper.CommentMapper.*;
import static com.company.blog.utils.SlugGenerator.toSlug;

@Service
public class CommentServiceImplementation implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;

    @Autowired
    public CommentServiceImplementation(
            CommentRepository commentRepository,
            PostRepository postRepository,
            UserRepository userRepository
    ) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<CommentDto> getAllComments(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<CommentEntity> comments = commentRepository.findAll(pageable);
        List<CommentEntity> listOfComments = comments.getContent();
        return listOfComments.stream().map(CommentMapper::mapToCommentDto).collect(Collectors.toList());
    }

    @Override
    public CommentDetailsDto getComment(String commentSlug) {
        CommentEntity comment = commentRepository
                .findBySlug(commentSlug)
                .orElseThrow(
                        () -> new CommentNotFoundException(
                                "Can not found comment by slug: " + commentSlug
                        )
                );
        return mapToCommentDetailsDto(comment);
    }

    @Override
    public List<CommentDto> findByPost(String postSlug) {
        List<CommentEntity> comments = commentRepository.findByPostSlug(postSlug);
        return comments.stream().map(CommentMapper::mapToCommentDto).collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> searchComment(String message) {
        List<CommentEntity> comments = commentRepository.searchByMessageIgnoreCase(message);
        return comments.stream().map(CommentMapper::mapToCommentDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, String postSlug) {
        String username = SecurityUtil.getSessionUser();
        Optional<UserEntity> user = userRepository.findByUsername(username);
        PostEntity post = postRepository
                .findBySlug(postSlug)
                .orElseThrow(
                        () -> new PostNotFoundException(
                                "Can not found post by slug: " + postSlug
                        )
                );
        CommentEntity comment = mapToComment(commentDto);
        comment.setUser(user.get());
        comment.setPost(post);
        comment.setSlug(toSlug(commentDto.getMessage()));
        commentRepository.save(comment);
        return mapToCommentDto(comment);
    }

    @Override
    public void deleteComment(String commentSlug) {
        if(!commentRepository.existsBySlug(commentSlug)) {
            throw new CategoryNotFoundException("Can not delete comment by current slug: " + commentSlug);
        }
        commentRepository.deleteBySlug(commentSlug);
    }
}
