package by.kazakevich.uniteddirect.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "date_time")
    private Timestamp datetime;
    private BigDecimal totalCost;
    private String status;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "orderdetails",
            joinColumns = { @JoinColumn(name = "order_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    Set<Product> products = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="user_id", insertable = false, updatable = false)
    private User user;

    public Order(Integer userId, Timestamp datetime, BigDecimal totalCost, String status) {
        this.userId = userId;
        this.datetime = datetime;
        this.totalCost = totalCost;
        this.status = status;
    }
}
