package by.kazakevich.uniteddirect.services;

import by.kazakevich.uniteddirect.domain.Product;
import by.kazakevich.uniteddirect.dto.AddProductDto;
import by.kazakevich.uniteddirect.dto.UpdateProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> findById(Integer id);

    List<Product> findAll();

    Page<Product> findAllByCategory(String category, Pageable pageable);

    List<Product> findAll(Sort sort);

    Page<Product> findAll(Pageable pageable);

    List<Product> findAllById(Iterable<Integer> iterable);

    long count();

    void deleteById(Integer integer);

    void delete(Product product);

    void deleteAll(Iterable<? extends Product> iterable);

    void deleteAll();

    <S extends Product> S save(S s);

    <S extends Product> List<S> saveAll(Iterable<S> iterable);

    Product updateProduct(Integer productId, UpdateProductDto product);

    Product addProduct(AddProductDto product);
}
