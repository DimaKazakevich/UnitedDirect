package by.kazakevich.uniteddirect.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer id;

    private String category;

    @OneToMany(mappedBy="category")
    @JsonIgnore
    private Set<Product> products;

    public Integer getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
