package fa.training.JWD.Practice.T01.service;

import fa.training.JWD.Practice.T01.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAllCategory();

    Optional<Category> getCategoryById(Integer id);
}
