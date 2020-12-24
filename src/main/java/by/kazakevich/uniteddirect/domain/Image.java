package by.kazakevich.uniteddirect.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "images")
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Integer id;

    @Column(name = "product_id")
    @JsonIgnore
    private Integer productId;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name="product_id", nullable=false, insertable = false, updatable = false)
    @JsonIgnore
    private Product product;

    public Image(String image, Integer productId) {
        this.image = image;
        this.productId = productId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
