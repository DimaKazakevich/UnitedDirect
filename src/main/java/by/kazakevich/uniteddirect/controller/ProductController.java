package by.kazakevich.uniteddirect.controller;

import by.kazakevich.uniteddirect.domain.Category;
import by.kazakevich.uniteddirect.domain.Product;
import by.kazakevich.uniteddirect.repository.CategoryRepository;
import by.kazakevich.uniteddirect.repository.ProductRepository;
import by.kazakevich.uniteddirect.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductController(ProductService productService, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> personList() {
        return productService.findAll();
    }

    @RequestMapping("/{category}")
    public List<Product> getByCategory(@PathVariable(value="category") String category) {
        return productRepository.findByCategory(category);
    }

    @RequestMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
