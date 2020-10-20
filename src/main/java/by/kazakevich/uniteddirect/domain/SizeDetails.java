package by.kazakevich.uniteddirect.domain;

import jdk.jfr.Enabled;
import lombok.Data;

import javax.persistence.*;

@Enabled
@Data
public class SizeDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "productid")
    Product product;

    @ManyToOne
    @JoinColumn(name = "sizeid")
    Size size;

    Integer count;
}
