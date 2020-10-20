package by.kazakevich.uniteddirect.controller;

import by.kazakevich.uniteddirect.domain.Category;
import by.kazakevich.uniteddirect.domain.Product;
import by.kazakevich.uniteddirect.repository.CategoryRepository;
import by.kazakevich.uniteddirect.services.ProductService;
import by.kazakevich.uniteddirect.services.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductService productService;
    private CategoryRepository categoryRepository;
    private SizeService sizeService;

    @Autowired
    public ProductController(ProductService productService, CategoryRepository categoryRepository, SizeService sizeService) {
        this.categoryRepository = categoryRepository;
        this.productService = productService;
        this.sizeService = sizeService;
    }

    @GetMapping
    public List<Product> personList() {
        return productService.findAll();
    }

    @RequestMapping("/{category}")
    public @ResponseBody
    List<Product> getByCategory(@PathVariable(value="category") String category) {
        return productService.findAllByCategory(category);
    }

    @RequestMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @RequestMapping("/{productId}/sizes")
    public @ResponseBody
    List<String> getByProductId(@PathVariable(value="productId") Integer productId) {
        return sizeService.findAllByProductId(productId);
    }
}
