package digital.ah.avanza.sgc.module.product.dto.brand;

import java.time.ZonedDateTime;

import digital.ah.avanza.sgc.module.product.entity.brand.BrandEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BrandOneDTO {

    private Long id;

    private String name;

    private String description;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    private Boolean status;

    public BrandOneDTO(BrandEntity brand) {
        this.id = brand.getId();
        this.name = brand.getName();
        this.description = brand.getDescription();
        this.createdAt = brand.getCreatedAt();
        this.updatedAt = brand.getUpdatedAt();
        this.status = brand.getStatus();
    }

}
