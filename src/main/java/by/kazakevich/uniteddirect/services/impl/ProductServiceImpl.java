package by.kazakevich.uniteddirect.services;

import by.kazakevich.uniteddirect.domain.Product;
import by.kazakevich.uniteddirect.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllByCategory(String category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public List<Product> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Product> findAllById(Iterable<Integer> iterable) {
        return productRepository.findAllById(iterable);
    }

    @Override
    public long count() {
        return productRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        productRepository.deleteById(integer);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public void deleteAll(Iterable<? extends Product> iterable) {
        productRepository.deleteAll();
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }

    @Override
    public <S extends Product> S save(S s) {
        return productRepository.save(s);
    }

    @Override
    public <S extends Product> List<S> saveAll(Iterable<S> iterable) {
        return productRepository.saveAll(iterable);
    }

}
