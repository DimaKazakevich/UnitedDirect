package by.kazakevich.uniteddirect.repository;

import by.kazakevich.uniteddirect.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order getById(Integer id);
}
