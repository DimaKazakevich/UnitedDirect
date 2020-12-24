package by.kazakevich.uniteddirect.services;

import by.kazakevich.uniteddirect.domain.Order;
import by.kazakevich.uniteddirect.domain.OrderDetail;
import by.kazakevich.uniteddirect.domain.Product;
import by.kazakevich.uniteddirect.dto.OrderDto;
import by.kazakevich.uniteddirect.security.jwt.JwtUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.security.Principal;
import java.util.List;

public interface OrderService {
    <S extends Order> S save(S s);

    void createOrder(Principal principal, List<OrderDto> orderDto);

    List<Order> findAll();

    List<OrderDetail> getAllByOrderId(Integer orderId);

    Order getById(Integer id);
}
