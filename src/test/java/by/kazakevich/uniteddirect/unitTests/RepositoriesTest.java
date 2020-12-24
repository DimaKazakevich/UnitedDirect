package by.kazakevich.uniteddirect.unitTests;

import by.kazakevich.uniteddirect.repository.ProductRepository;
import by.kazakevich.uniteddirect.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RepositoriesTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void getProductById() {
        Assert.assertNotNull(productRepository.findById(4));
    }

    @Test
    public void getAllProductsByCategory() {
        Assert.assertNotNull(productRepository.findAllByCategory("kits", PageRequest.of(0,8)));
    }

    @Test
    public void getUserByEmail() {
        Assert.assertNull(userRepository.findByEmail("example@gmail.com"));
    }

    @Test
    public void getAllProducts() {
        Assert.assertNotNull(productRepository.findAll());
    }
}
