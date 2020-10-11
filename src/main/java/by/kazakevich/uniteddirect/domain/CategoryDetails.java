package by.kazakevich.uniteddirect.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "categorydetails")
public class CategoryDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "productid")
    Product product;

    @ManyToOne
    @JoinColumn(name = "categoryid")
    Category category;
}
