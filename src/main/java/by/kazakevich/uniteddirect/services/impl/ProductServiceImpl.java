package by.kazakevich.uniteddirect.services.impl;

import by.kazakevich.uniteddirect.domain.Image;
import by.kazakevich.uniteddirect.domain.Product;
import by.kazakevich.uniteddirect.domain.SizeDetails;
import by.kazakevich.uniteddirect.domain.SizeDetailsId;
import by.kazakevich.uniteddirect.dto.AddProductDto;
import by.kazakevich.uniteddirect.dto.UpdateProductDto;
import by.kazakevich.uniteddirect.repository.ImageRepository;
import by.kazakevich.uniteddirect.repository.ProductRepository;
import by.kazakevich.uniteddirect.repository.SizeDetailsRepository;
import by.kazakevich.uniteddirect.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private final SizeDetailsRepository detailsRepository;

    public ProductServiceImpl(ProductRepository productRepository, ImageRepository imageRepository, SizeDetailsRepository detailsRepository) {
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
        this.detailsRepository = detailsRepository;
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

/*    @Override
    public List<Product> findAllByCategoryId(Integer category, Pageable pageable) {
        return productRepository.findAllByCategoryId(category, pageable);
    }*/

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findAllByCategory(String category, Pageable pageable) {
        return productRepository.findAllByCategory(category, pageable);
    }

    @Override
    public List<Product> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
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

    @Override
    public Product updateProduct(Integer productId, UpdateProductDto productDto) {
        Optional<Product> product = productRepository.findById(productId);
        product.get().setTitle(productDto.getTitle());
        product.get().setDescription(productDto.getDescription());
        product.get().setPrice(productDto.getPrice());
        productRepository.save(product.get());
        return product.get();
    }

    @Override
    public Product addProduct(AddProductDto product) {
        Product newProduct = new Product(
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory());

        productRepository.save(newProduct);
        for (String imageURL: product.getImages()) {
            Image image = new Image(imageURL, newProduct.getId());
            imageRepository.save(image);
        }

        for (Integer sizeId: product.getSizes()) {
            SizeDetailsId sizeDetailsId = new SizeDetailsId();
            sizeDetailsId.setProductId(newProduct.getId());
            sizeDetailsId.setSizeId(sizeId);
            SizeDetails sizeDetails = new SizeDetails(sizeDetailsId);
            detailsRepository.save(sizeDetails);
        }
        return newProduct;
    }
}
