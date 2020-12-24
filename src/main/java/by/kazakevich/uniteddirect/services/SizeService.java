package by.kazakevich.uniteddirect.services;

import by.kazakevich.uniteddirect.domain.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface SizeService {
    List<Size> findAll();

    List<Size> findAllByProductId(Integer productId);

    List<Size> findAll(Sort sort);

    Page<Size> findAll(Pageable pageable);

    List<Size> findAllById(Iterable<Integer> iterable);

    long count();

    void deleteById(Integer integer);

    void delete(Size size);

    void deleteAll(Iterable<? extends Size> iterable);

    void deleteAll();
    <S extends Size> S save(S s);

    <S extends Size> List<S> saveAll(Iterable<S> iterable);
}
