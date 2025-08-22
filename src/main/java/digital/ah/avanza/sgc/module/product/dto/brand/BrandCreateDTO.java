package digital.ah.avanza.sgc.module.product.dto.brand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BrandCreateDTO {

    private Long company;

    private String name;

    private String description;

}
