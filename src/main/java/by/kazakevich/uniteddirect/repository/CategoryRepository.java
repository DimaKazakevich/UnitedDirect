package by.kazakevich.uniteddirect.repository;

import by.kazakevich.uniteddirect.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
