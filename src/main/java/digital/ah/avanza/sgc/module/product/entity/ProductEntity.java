package digital.ah.avanza.sgc.module.product.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import digital.ah.avanza.sgc.module.company.entity.CompanyEntity;
import digital.ah.avanza.sgc.module.product.entity.brand.BrandEntity;
import digital.ah.avanza.sgc.module.product.entity.category.CategoryEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "products")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;

    private String internalCode;

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

    @Column(updatable = false)
    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    private Boolean status;

    @PrePersist
    private void prePersist() {
        createdAt = ZonedDateTime.now();
        updatedAt = ZonedDateTime.now();
        this.status = true;
    }

    @PreUpdate
    private void preUpdate() {
        updatedAt = ZonedDateTime.now();
    }

}
