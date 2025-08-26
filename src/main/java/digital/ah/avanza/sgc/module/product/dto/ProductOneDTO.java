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
public class ProductOneDTO {

    private Long id;

    private String category;

    private String brand;

    private String barCode;

    private String internalCode;

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

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    private Boolean status;

    public ProductOneDTO(ProductEntity product) {
        this.id = product.getId();
        this.category = product.getCategory().getName();
        this.brand = product.getBrand().getName();
        this.internalCode = product.getInternalCode();
        this.barCode = product.getBarCode();
        this.name = product.getName();
        this.description = product.getDescription();
        this.model = product.getModel();
        this.purchasePrice = product.getPurchasePrice();
        this.salePrice = product.getSalePrice();
        this.minStock = product.getMinStock();
        this.taxable = product.getTaxable();
        this.expirationDate = product.getExpirationDate();
        this.warrantyMonths = product.getWarrantyMonths();
        this.image = product.getImage();
        this.createdAt = product.getCreatedAt();
        this.updatedAt = product.getUpdatedAt();
        this.status = product.getStatus();
    }

}
