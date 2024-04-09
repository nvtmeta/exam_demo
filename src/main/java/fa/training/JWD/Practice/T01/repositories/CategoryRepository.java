package fa.training.JWD.Practice.T01.repositories;

import fa.training.JWD.Practice.T01.models.Category;
import fa.training.JWD.Practice.T01.models.Cert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Integer> {
}
