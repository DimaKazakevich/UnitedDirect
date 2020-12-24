package by.kazakevich.uniteddirect.services.impl;

import by.kazakevich.uniteddirect.domain.Order;
import by.kazakevich.uniteddirect.domain.OrderDetail;
import by.kazakevich.uniteddirect.domain.User;
import by.kazakevich.uniteddirect.dto.OrderDto;
import by.kazakevich.uniteddirect.repository.OrderDetailsRepository;
import by.kazakevich.uniteddirect.repository.OrderRepository;
import by.kazakevich.uniteddirect.repository.UserRepository;
import by.kazakevich.uniteddirect.security.jwt.JwtUser;
import by.kazakevich.uniteddirect.services.OrderService;
import com.sun.security.auth.UserPrincipal;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public <S extends Order> S save(S s) {
        return orderRepository.save(s);
    }

    public void createOrder(Principal principal, List<OrderDto> orderDto) {
        BigDecimal totalCost = computeTotalCost(orderDto);
        Integer userId = userRepository.findByEmail(principal.getName()).getId();

        Order order = new Order(userId, new Timestamp(new Date().getTime()), totalCost, "In processing");
        orderRepository.save(order);

        for (OrderDto oneOrder: orderDto) {
            orderDetailsRepository.save(new OrderDetail(
                                                    order.getId(),
                                                    oneOrder.getId(),
                                                    oneOrder.getSize(),
                                                    oneOrder.getQuantity()));
        }
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    private BigDecimal computeTotalCost(List<OrderDto> orderDto) {
        BigDecimal totalCost = new BigDecimal(0);
        for (OrderDto order: orderDto) {
            totalCost = totalCost.add(order.getPrice().multiply(BigDecimal.valueOf(order.getQuantity())));
        }

        return totalCost;
    }

    public List<OrderDetail> getAllByOrderId(Integer orderId) {
        return orderDetailsRepository.findAllByOrderId(orderId);
    }

    @Override
    public Order getById(Integer id) {
        return orderRepository.getById(id);
    }
}
