package digital.ah.avanza.sgc.module.product.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryUpdateDTO {

    private Long id;

    private String name;

    private String description;
}
