package digital.ah.avanza.sgc.module.product.dto.category;

import java.time.ZonedDateTime;

import digital.ah.avanza.sgc.module.product.entity.category.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryOneDTO {

    private Long id;

    private String name;

    private String description;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    private Boolean status;

    public CategoryOneDTO(CategoryEntity category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
        this.createdAt = category.getCreatedAt();
        this.updatedAt = category.getUpdatedAt();
        this.status = category.getStatus();
    }

}
