package by.kazakevich.uniteddirect.repository;

import by.kazakevich.uniteddirect.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "select * from Products join Categories on Products.category_id = Categories.category_id where Category = ?1", nativeQuery = true)
    Page<Product> findAllByCategory(String category, Pageable pageable);

    Optional<Product> findById(Integer id);
}