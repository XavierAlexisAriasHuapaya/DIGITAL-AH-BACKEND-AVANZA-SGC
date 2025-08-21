package digital.ah.avanza.sgc.module.master.dto;

import java.util.List;

import digital.ah.avanza.sgc.module.master.entity.CatalogItemEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MasterCatalogOneDTO {

    private Long id;

    private String name;

    private List<CatalogItemEntity> catalog_items;

    private Boolean status;
}
