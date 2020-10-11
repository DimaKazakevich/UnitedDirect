package by.kazakevich.uniteddirect.services;

import by.kazakevich.uniteddirect.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductService {

     List<Product> findAll();

    public List<Product> findAll(Sort sort);
    public Page<Product> findAll(Pageable pageable);

    public List<Product> findAllById(Iterable<Integer> iterable);

    public long count();

    public void deleteById(Integer integer);

    public void delete(Product product);

    public void deleteAll(Iterable<? extends Product> iterable);

    public void deleteAll();
    public <S extends Product> S save(S s);

    public <S extends Product> List<S> saveAll(Iterable<S> iterable);
}
