package by.kazakevich.uniteddirect.dto;

import by.kazakevich.uniteddirect.domain.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDto {
    private Integer id;
    private String title;
    private String description;
    private BigDecimal price;
    private Category category;
    private Integer brandId;
    private String size;
    private Integer quantity;
}
