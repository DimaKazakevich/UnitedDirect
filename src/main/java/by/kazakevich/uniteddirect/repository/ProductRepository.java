package by.kazakevich.uniteddirect.repository;

import by.kazakevich.uniteddirect.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
