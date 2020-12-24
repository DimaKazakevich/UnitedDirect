package by.kazakevich.uniteddirect.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class SizeDetailsId implements Serializable {
    @Column(name = "product_Id")
    Integer productId;

    @Column(name = "size_Id")
    Integer sizeId;
}
