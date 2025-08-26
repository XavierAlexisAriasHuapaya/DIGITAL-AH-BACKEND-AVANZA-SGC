package digital.ah.avanza.sgc.module.product.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductCreateDTO {

    private Long company;

    private Long category;

    private Long brand;

    private String barCode;

    private String name;

    private String description;

    private String model;

    private BigDecimal purchasePrice;

    private BigDecimal salePrice;

    private Integer minStock;

    private Boolean taxable;

    private LocalDate expirationDate;

    private Integer warrantyMonths;

    private String image;
    
}
