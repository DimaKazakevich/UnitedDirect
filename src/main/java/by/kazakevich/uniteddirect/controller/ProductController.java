package by.kazakevich.uniteddirect.controller;

import by.kazakevich.uniteddirect.domain.Product;
import by.kazakevich.uniteddirect.repository.CategoryDetailsRepository;
import by.kazakevich.uniteddirect.repository.ProductRepository;
import by.kazakevich.uniteddirect.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private ProductRepository productRepository;
    private CategoryDetailsRepository categoryDetailsRepository;

    @Autowired
    public ProductController(ProductService productService, CategoryDetailsRepository categoryDetailsRepository) {

        this.productService = productService;
        this.categoryDetailsRepository = categoryDetailsRepository;
    }

    @GetMapping
    public List<Product> personList() {
        return productService.findAll();
    }

    @RequestMapping("/{category}")
    public List<Product> getByCategory(@PathVariable(value="category") String category,
                String someAttr) {
        //just for example, real implementation will be later
        List<Product> products = new ArrayList<>();
        products.add(new Product(5, "example", "Descriptin", BigDecimal.valueOf(2)));
        return products;
    }
}
