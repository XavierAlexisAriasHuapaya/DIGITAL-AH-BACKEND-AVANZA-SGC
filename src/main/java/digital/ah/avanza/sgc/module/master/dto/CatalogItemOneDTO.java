package digital.ah.avanza.sgc.module.master.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatalogItemOneDTO {

    private Long id;

    private String name;

    private Boolean status;

}
