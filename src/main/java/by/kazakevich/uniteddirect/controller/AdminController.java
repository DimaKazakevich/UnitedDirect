package by.kazakevich.uniteddirect.controller;

import by.kazakevich.uniteddirect.domain.Order;
import by.kazakevich.uniteddirect.domain.OrderDetail;
import by.kazakevich.uniteddirect.domain.Product;
import by.kazakevich.uniteddirect.domain.User;
import by.kazakevich.uniteddirect.dto.AddProductDto;
import by.kazakevich.uniteddirect.dto.UpdateProductDto;
import by.kazakevich.uniteddirect.logging.Loggable;
import by.kazakevich.uniteddirect.services.OrderService;
import by.kazakevich.uniteddirect.services.ProductService;
import by.kazakevich.uniteddirect.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final ProductService productService;
    private final UserService userService;
    private final JavaMailSender emailSender;
    private final OrderService orderService;

    public AdminController(ProductService productService,
                           UserService userService,
                           JavaMailSender emailSender,
                           OrderService orderService) {
        this.productService = productService;
        this.userService = userService;
        this.emailSender = emailSender;
        this.orderService = orderService;
    }

    @Operation(summary = "Update product", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update product by id",
                    content = {@Content(mediaType = "application/json")})
    })
    @PutMapping("products/{productId}")
    @Loggable
    public ResponseEntity<Product> updateProduct(@PathVariable(value="productId") Integer productId,
                                                          @RequestBody UpdateProductDto product) {
        Product updatedProduct =  productService.updateProduct(productId, product);

        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @Operation(summary = "Add new product", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Add product",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("/products")
    @Loggable
    public ResponseEntity<Product> addProduct(@RequestBody AddProductDto product) {
        Product newProduct = productService.addProduct(product);

        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }

    @Operation(summary = "Get all users orders", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return orders",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/orders")
    @Loggable
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders =  orderService.findAll();

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @Operation(summary = "Send email message to user", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Send message using template and using gmail STMP",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("send-email")
    @Loggable
    public ResponseEntity<Object> sendEmailMessage(String email, Integer orderId) {
        SimpleMailMessage message = new SimpleMailMessage();
        User user = userService.findByEmail(email);
        message.setTo(user.getEmail());
        message.setSubject("United Direct Order");
        StringBuilder msg = new StringBuilder("Hi, " + email +". Your order has just been sent and will be with you in 6-7 business days. \n\n");

        for (OrderDetail orderDetail : orderService.getAllByOrderId(orderId)) {
            msg.append("✓ Product: ").append(productService.findById(orderDetail.getOrderId()).get().getTitle())
                    .append(", Size: ").append(orderDetail.getSize())
                    .append(", Quantity: ").append(orderDetail.getQuantity())
                    .append("\n");
        }
        msg.append("With best regards, United Direct!");

        message.setText(String.valueOf(msg));
        this.emailSender.send(message);

        Order order = orderService.getById(orderId);
        order.setStatus("Sent");
        orderService.save(order);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
