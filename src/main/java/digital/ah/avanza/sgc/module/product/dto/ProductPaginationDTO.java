package digital.ah.avanza.sgc.module.product.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import digital.ah.avanza.sgc.module.product.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductPaginationDTO {

    private Long id;

    private String category;

    private String brand;

    private String internalCode;

    private String barCode;

    private String name;

    private String model;

    private BigDecimal salePrice;

    private LocalDate expirationDate;

    private ZonedDateTime createdAt;

    private Boolean status;

    public ProductPaginationDTO(ProductEntity product) {
        this.id = product.getId();
        this.category = product.getCategory().getName();
        this.brand = product.getBrand().getName();
        this.internalCode = product.getInternalCode();
        this.barCode = product.getBarCode();
        this.name = product.getName();
        this.model = product.getModel();
        this.salePrice = product.getSalePrice();
        this.expirationDate = product.getExpirationDate();
        this.createdAt = product.getCreatedAt();
        this.status = product.getStatus();
    }

}
