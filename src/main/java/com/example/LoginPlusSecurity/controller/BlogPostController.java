package com.example.LoginPlusSecurity.controller;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.LoginPlusSecurity.model.BlogPost;
import com.example.LoginPlusSecurity.repository.BlogPostRepository;
import com.example.LoginPlusSecurity.repository.CategoryRepository;
import com.example.LoginPlusSecurity.repository.CommentRepository;
import com.example.LoginPlusSecurity.service.BlogPostService;

@Controller
public class BlogPostController {

    private final BlogPostService blogPostService;
    private final CategoryRepository categoryRepository;
    private final BlogPostRepository blogPostRepository;
    private final CommentRepository commentRepository;

    public BlogPostController(BlogPostService blogPostService, 
                               CategoryRepository categoryRepository, 
                               BlogPostRepository blogPostRepository, 
                               CommentRepository commentRepository) {
        this.blogPostService = blogPostService;
        this.categoryRepository = categoryRepository;
        this.blogPostRepository = blogPostRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/admin/manage")
    public String home() {
        return "manage";
    }

    @GetMapping("/")
    public String showHomePage(@RequestParam(defaultValue = "latest") String filter,
                               @RequestParam(defaultValue = "0") int page,
                               Model model) {
        return paginateAndRenderHomePage(blogPostService.homepageSorting(page, 5, filter), filter, page, model, "home");
    }

    @GetMapping("/search")
    public String searchBlogPosts(@RequestParam String query,
                                @RequestParam(defaultValue = "latest") String filter,
                                @RequestParam(defaultValue = "0") int page,
                                Model model) {
        Page<BlogPost> blogPosts = blogPostService.titleSorting(query, page, 5, filter); // Reuse service method
        model.addAttribute("blogPosts", blogPosts.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", blogPosts.getTotalPages());
        model.addAttribute("query", query); // Ensure query is passed to the template
        model.addAttribute("filter", filter); // Ensure filter is passed for UI consistency
        return "searchResults"; // Consistent with other endpoints
    }


    @GetMapping("/category/{id}")
    public String getBlogPostsByCategory(@PathVariable("id") Long categoryId,
                                         @RequestParam(defaultValue = "latest") String filter,
                                         @RequestParam(defaultValue = "0") int page,
                                         Model model) {
        Page<BlogPost> blogPosts = blogPostService.categorySorting(categoryId, page, 5, filter);
        String categoryName = categoryRepository.getCategoryNameById(categoryId);
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("filterCategoryId", categoryId);
        return paginateAndRenderHomePage(blogPosts, filter, page, model, "categoryResults");
    }

    @GetMapping("/page/{pageNumber}")
    public String showPaginatedHomePage(@PathVariable int pageNumber, 
                                        @RequestParam(defaultValue = "latest") String sortBy, 
                                        Model model) {
        return paginateAndRenderHomePage(blogPostService.homepageSorting(pageNumber, 5, sortBy), sortBy, pageNumber, model, "home");
    }

    @GetMapping("/blog/{slug}")
    public String viewBlogPost(@PathVariable String slug, Model model) {
        BlogPost blogPost = blogPostService.findBySlug(slug);
        blogPost.setViews(blogPost.getViews() + 1);
        blogPostRepository.save(blogPost); // Update view count
        model.addAttribute("blogPost", blogPost);
        model.addAttribute("comments", commentRepository.findByBlogPostId(blogPost.getId()));
        return "viewBlog";
    }

    @GetMapping("/admin/blog/create")
    public String showCreateBlogForm(Model model) {
        model.addAttribute("blogPost", new BlogPost());
        model.addAttribute("categories", categoryRepository.findAll());
        return "createBlog";
    }

    @PostMapping("/admin/blog/create")
    public String createBlogPost(@ModelAttribute BlogPost blogPost, Principal principal) {
        blogPost.setAuthorName(principal.getName()); // Set logged-in user's name
        blogPostService.saveBlogPost(blogPost);
        return "redirect:/";
    }

    // Helper method for pagination setup
    private String paginateAndRenderHomePage(Page<BlogPost> blogPosts, String filter, int page, Model model, String viewName) {
        model.addAttribute("blogPosts", blogPosts.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", blogPosts.getTotalPages());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("filter", filter);
        return viewName;
    }
}
