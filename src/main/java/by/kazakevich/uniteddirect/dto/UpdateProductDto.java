package by.kazakevich.uniteddirect.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateProductDto {
    private String title;
    private String description;
    private BigDecimal price;
}
