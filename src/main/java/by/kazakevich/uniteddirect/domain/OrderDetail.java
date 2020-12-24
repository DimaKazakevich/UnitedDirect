package by.kazakevich.uniteddirect.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "OrderDetails")
@Data
@NoArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_details_id")
    private Integer id;

    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "product_id")
    private Integer productId;
    private String size;
    private Integer quantity;

    public OrderDetail(Integer orderId, Integer productId, String size, Integer quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.size = size;
        this.quantity = quantity;
    }
}
