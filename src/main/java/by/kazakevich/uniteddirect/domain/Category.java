package by.kazakevich.uniteddirect.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String category;

//    @OneToMany(mappedBy = "category")
//    Set<CategoryDetails> categoryDetailsSet;
}
