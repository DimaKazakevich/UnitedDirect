package by.kazakevich.uniteddirect.repository;

import by.kazakevich.uniteddirect.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "select * from Products join CategoryDetails on Products.Id = CategoryDetails.ProductId join Categories on CategoryDetails.CategoryId = Categories.Id where Category = ?1", nativeQuery = true)
    List<Product> findAllByCategory(String category);
}