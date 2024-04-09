package fa.training.JWD.Practice.T01.service.impl;

import fa.training.JWD.Practice.T01.models.Category;
import fa.training.JWD.Practice.T01.repositories.CategoryRepository;
import fa.training.JWD.Practice.T01.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }



    @Override
    public Optional<Category> getCategoryById(Integer id) {
        return categoryRepository.findById(id);
    }
}
