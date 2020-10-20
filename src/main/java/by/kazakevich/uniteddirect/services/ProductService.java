package by.kazakevich.uniteddirect.services;

import by.kazakevich.uniteddirect.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    List<Product> findAllByCategory(String category);

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
}
