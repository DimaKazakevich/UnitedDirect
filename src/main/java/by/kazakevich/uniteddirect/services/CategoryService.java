package by.kazakevich.uniteddirect.services;

import by.kazakevich.uniteddirect.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    List<Category> findAll(Sort sort);

    Page<Category> findAll(Pageable pageable);

    List<Category> findAllById(Iterable<Integer> iterable);

    long count();

    void deleteById(Integer id);

    void delete(Category category);

    void deleteAll(Iterable<? extends Category> iterable);

    void deleteAll();
    <S extends Category> S save(S s);

    <S extends Category> List<S> saveAll(Iterable<S> iterable);
}
