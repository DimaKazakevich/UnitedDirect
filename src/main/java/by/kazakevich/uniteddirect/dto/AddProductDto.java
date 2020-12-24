package by.kazakevich.uniteddirect.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AddProductDto {
    private String title;
    private String description;
    private BigDecimal price;
    private List<String> images;
    private Integer category;
    private List<Integer> sizes;
}
