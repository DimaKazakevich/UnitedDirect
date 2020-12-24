package by.kazakevich.uniteddirect.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Sizes")
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "size_id")
    private Integer id;

    @Column(name = "size")
    private String size;

    public String getSize() {
        return size;
    }

    @ManyToMany(mappedBy = "sizes")
    private Set<Product> products = new HashSet<>();
}
