package com.example.LoginPlusSecurity.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.LoginPlusSecurity.model.BlogPost;
import com.example.LoginPlusSecurity.repository.BlogPostRepository;

@Service
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;

    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    public Page<BlogPost> homepageSorting(int page, int size, String sortBy) {
        Pageable pageable = createPageable(page, size, sortBy);
        return blogPostRepository.findAll(pageable);
    }

    public Page<BlogPost> titleSorting(String query, int page, int size, String sortBy) {
        Pageable pageable = createPageable(page, size, sortBy);
        return blogPostRepository.findByTitleContainingIgnoreCase(query, pageable);
    }

    public Page<BlogPost> categorySorting(Long categoryId, int page, int size, String sortBy) {
        Pageable pageable = createPageable(page, size, sortBy);
        return blogPostRepository.findByCategoryId(categoryId, pageable);
    }

    public void saveBlogPost(BlogPost blogPost) {
        blogPost.setSlug(generateSlug(blogPost.getTitle()));
        blogPostRepository.save(blogPost);
    }

    public BlogPost findBySlug(String slug) {
        return blogPostRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Blog post not found!"));
    }

    private String generateSlug(String title) {
        return title.toLowerCase()
                    .replaceAll("[^a-z0-9\\s-]", "")
                    .trim()
                    .replaceAll("\\s+", "-")
                    .replaceAll("^-|-$", "");
    }

    private Pageable createPageable(int page, int size, String sortBy) {
        Sort sort = getSortOrder(sortBy);
        return PageRequest.of(page, size, sort);
    }

    private Sort getSortOrder(String sortBy) {
        return switch (sortBy) {
            case "oldest" -> Sort.by("creationDate").ascending();
            case "views" -> Sort.by("views").descending();
            default -> Sort.by("creationDate").descending(); // Default to latest
        };
    }
}
