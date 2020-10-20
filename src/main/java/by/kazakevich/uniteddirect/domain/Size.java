package by.kazakevich.uniteddirect.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Sizes")
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String size;
}
