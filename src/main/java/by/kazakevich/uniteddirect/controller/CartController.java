package by.kazakevich.uniteddirect.controller;

import by.kazakevich.uniteddirect.dto.OrderDto;
import by.kazakevich.uniteddirect.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final OrderService orderService;

    @Autowired
    public CartController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("order")
    public ResponseEntity<?> createNewOrder(Principal principal, @RequestBody List<OrderDto> products) {
        orderService.createOrder(principal,products);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
