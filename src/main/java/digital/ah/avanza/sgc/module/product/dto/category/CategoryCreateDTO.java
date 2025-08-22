package digital.ah.avanza.sgc.module.product.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryCreateDTO {

    private Long company;

    private String name;

    private String description;

}
