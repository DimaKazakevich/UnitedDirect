package by.kazakevich.uniteddirect.controller;

import by.kazakevich.uniteddirect.domain.Category;
import by.kazakevich.uniteddirect.domain.Product;
import by.kazakevich.uniteddirect.domain.Size;
import by.kazakevich.uniteddirect.services.CategoryService;
import by.kazakevich.uniteddirect.services.ProductService;
import by.kazakevich.uniteddirect.services.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final SizeService sizeService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService, SizeService sizeService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.sizeService = sizeService;
    }

    @GetMapping("/{category}")
    public @ResponseBody
    ResponseEntity<Page<Product>> getAllProductsByCategory(@PathVariable(value="category") String category,
                                                           @RequestParam(value = "page") Integer page) {

        Page<Product> products = productService.findAllByCategory(category, PageRequest.of(page,8));

        if(products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.findAll();

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{productId}/sizes")
    public ResponseEntity<List<Size>> getAllSizesByProductId(@PathVariable(value="productId") Integer productId) {
        List<Size> sizes = sizeService.findAllByProductId(productId);

        return new ResponseEntity<>(sizes, HttpStatus.OK);
    }

    @GetMapping("/{category}/pages")
    public ResponseEntity<Map<Object, Object>> getPagesCount(@PathVariable(value="category") String category) {
        int pagesCount = productService.findAllByCategory(category, PageRequest.of(1,8)).getTotalPages();
        Map<Object, Object> response = new HashMap<>();
        response.put("pagesCount", pagesCount);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
