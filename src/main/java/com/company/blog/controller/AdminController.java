package com.company.blog.controller;

import com.company.blog.domain.CategoryEntity;
import com.company.blog.domain.PostEntity;
import com.company.blog.dto.category.CategoryDetailsDto;
import com.company.blog.dto.category.CategoryDto;
import com.company.blog.dto.comment.CommentDetailsDto;
import com.company.blog.dto.comment.CommentDto;
import com.company.blog.dto.post.PostDetailsDto;
import com.company.blog.dto.post.PostDto;
import com.company.blog.dto.profile.UserProfileDetailsDto;
import com.company.blog.dto.profile.UserProfileDto;
import com.company.blog.dto.role.RoleDetailsDto;
import com.company.blog.dto.role.RoleDto;
import com.company.blog.dto.user.UserDetailsDto;
import com.company.blog.dto.user.UserDto;
import com.company.blog.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private CategoryService categoryService;
    private PostService postService;
    private CommentService commentService;
    private UserService userService;
    private RoleService roleService;
    private ProfileService profileService;

    @Autowired
    public AdminController(
            CategoryService categoryService,
            PostService postService,
            CommentService commentService,
            UserService userService,
            RoleService roleService,
            ProfileService profileService
    ) {
        this.categoryService = categoryService;
        this.postService = postService;
        this.commentService = commentService;
        this.userService = userService;
        this.roleService = roleService;
        this.profileService = profileService;
    }

    @GetMapping("/categories")
    public List<CategoryDto> getCategories(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize
    ){
        return categoryService.getAllCategories(pageNumber, pageSize);
    }

    @GetMapping("/categories/{categorySlug}")
    public CategoryDetailsDto getCategory(@PathVariable(value = "categorySlug") String categorySlug) {
        return categoryService.getCategoryBySlug(categorySlug);
    }

    @GetMapping("/categories/search")
    public List<CategoryDto> searchCategoryByName(@RequestParam(value = "name") String name) {
        return categoryService.searchCategory(name);
    }

    @PostMapping("/categories/create")
    public CategoryDto createNewCategory(@Valid @RequestBody CategoryDto categoryDto) {
        return categoryService.createCategory(categoryDto);
    }

    @PutMapping("/categories/{categorySlug}/update")
    public CategoryDto updateCurrentCategory(
            @PathVariable(value = "categorySlug") String categorySlug,
            @Valid @RequestBody CategoryDto categoryDto
    ) {
       return categoryService.updateCategory(categoryDto, categorySlug);
    }

    @DeleteMapping("/categories/{categorySlug}/delete")
    public String deleteCategoryBySlug(@PathVariable(value = "categorySlug") String categorySlug){
        categoryService.deleteCategory(categorySlug);
        return "Successful Deleted!";
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

    @PostMapping("/posts/category/{categorySlug}/create")
    public PostDto createPublication(
            @PathVariable(value = "categorySlug") String categorySlug,
            @Valid @RequestBody PostDto postDto
    ) {
        return postService.createPost(postDto, categorySlug);
    }

    @PutMapping("/posts/{postSlug}/update")
    public PostDto updatePublication(
            @PathVariable(value = "postSlug") String postSlug,
            @Valid @RequestBody PostDetailsDto postDetailsDto
    ){
        return postService.updatePost(postDetailsDto, postSlug);
    }

    @DeleteMapping("/posts/{postSlug}/delete")
    public String deletePost(@PathVariable(value = "postSlug") String postSlug) {
        postService.deletePost(postSlug);
        return "Successful deleted!";
    }

    @GetMapping("/comments")
    public List<CommentDto> getListOfComments(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize
    ) {
        return commentService.getAllComments(pageNumber, pageSize);
    }

    @GetMapping("/comments/{commentSlug}")
    public CommentDetailsDto getCommentDetails(@PathVariable(value = "commentSlug") String commentSlug) {
        return commentService.getComment(commentSlug);
    }

    @GetMapping("/posts/{postSlug}/comments")
    public List<CommentDto> getCommentForPost(@PathVariable(value = "postSlug") String postSlug) {
        return commentService.findByPost(postSlug);
    }

    @GetMapping("/comments/search")
    public List<CommentDto> searchComments(@RequestParam(value = "message") String message) {
        return commentService.searchComment(message);
    }

    @DeleteMapping("/comments/{commentSlug}/delete")
    public String deleteComment(@PathVariable(value = "commentSlug") String commentSlug) {
        commentService.deleteComment(commentSlug);
        return "Successful Deleted!";
    }

    @GetMapping("/users")
    public List<UserDto> getAllUsers(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize
    ) {
        return userService.getAllUsers(pageNumber, pageSize);
    }

    @GetMapping("/users/{username}")
    public UserDetailsDto getUser(@PathVariable(value = "username") String username){
        return userService.getUser(username);
    }

    @GetMapping("/users/search")
    public List<UserDto> searchUser(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "email") String email
    ) {
        return userService.searchUserByUsernameOrEmail(username, email);
    }

    @DeleteMapping("/users/{username}/delete")
    public String deleteUser(@PathVariable(value = "username") String username) {
        userService.deleteUser(username);
        return "Successful deleted!";
    }

    @GetMapping("/roles")
    public List<RoleDto> getRoles() {
        return roleService.getRoles();
    }

    @GetMapping("/roles/{name}")
    public RoleDetailsDto getRole(@PathVariable(value = "name") String name) {
        return roleService.getRole(name);
    }

    @GetMapping("/profiles")
    public List<UserProfileDto> getAllProfiles(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize
    ) {
        return profileService.findAllProfiles(pageNumber, pageSize);
    }

    @GetMapping("/profiles/{username}")
    public UserProfileDetailsDto getUsersProfileDetails(@PathVariable(value = "username") String username) {
        return profileService.findDetailsByUsername(username);
    }

    @GetMapping("/profiles/search")
    public List<UserProfileDto> searchProfiles(
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "city") String city,
            @RequestParam(value = "country") String country
    ){
        return profileService.searchProfile(firstName, lastName, city, country);
    }

    @DeleteMapping("/profiles/{username}/delete")
    public String deleteUserProfile(@PathVariable(value = "username") String username) {
        profileService.deleteProfile(username);
        return "Successful deleted!";
    }
}
