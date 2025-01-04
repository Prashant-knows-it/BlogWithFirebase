package com.example.LoginPlusSecurity.Seeder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.LoginPlusSecurity.model.Category;
import com.example.LoginPlusSecurity.repository.CategoryRepository;

@Component
public class CategoryDataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public CategoryDataLoader(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            categoryRepository.save(new Category("Technology"));
            categoryRepository.save(new Category("Politics"));

            categoryRepository.save(new Category("Health"));
            categoryRepository.save(new Category("Mental Health"));
            categoryRepository.save(new Category("Self-Help"));
            categoryRepository.save(new Category("Psychology"));
            categoryRepository.save(new Category("Life Lessons"));

            categoryRepository.save(new Category("Relationships"));
            categoryRepository.save(new Category("Parenting"));
            categoryRepository.save(new Category("For Male"));
            categoryRepository.save(new Category("For Female"));

            categoryRepository.save(new Category("How-To"));
            categoryRepository.save(new Category("Things-To-Do"));
            categoryRepository.save(new Category("Tactics"));

            categoryRepository.save(new Category("Book Review"));
            categoryRepository.save(new Category("Feelings"));
            categoryRepository.save(new Category("Adult"));
            categoryRepository.save(new Category("Other"));

        }
    }
}