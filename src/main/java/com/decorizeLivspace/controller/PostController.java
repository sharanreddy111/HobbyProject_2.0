package com.decorizeLivspace.controller;

import com.decorizeLivspace.model.Post;
import com.decorizeLivspace.repository.PostRepository;
import com.decorizeLivspace.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;



    @GetMapping("/tutorials")
    public String getAllPosts(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "pages-tutorials";
    }

    @GetMapping("/adminDashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "adminDashboard";
    }

    @GetMapping("/post/{id}")
    public String getPostById(@PathVariable("id") Long id, Model model) {
        Post blogPost = postService.getPostById(id);
        model.addAttribute("post", blogPost);
        return "post-viewByID";
    }

    @GetMapping("/post/new")
    public String showPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "post-create";
    }

    @PostMapping("/post/new")
    public String createPost(@ModelAttribute("post") Post blogPost) {
        postService.savePost(blogPost);
        return "redirect:/tutorials";
    }

    @GetMapping("/post/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Post blogPost = postService.getPostById(id);
        model.addAttribute("post", blogPost);
        return "post-update";
    }

    @PostMapping("/post/edit/{id}")
    public String updatePost(@PathVariable("id") Long id, @ModelAttribute("post") Post updatedBlogPost) {
        Post blogPost = postService.getPostById(id);
        postService.updatePost(id, updatedBlogPost);
        return "redirect:/tutorials";
    }

    @GetMapping("/post/delete/{id}")
    public String deletePost(@PathVariable("id") Long id) {
        Post blogPost = postService.getPostById(id);
        postService.deletePost(id);
        return "redirect:/adminDashboard";
    }
}
