package digital.ah.avanza.sgc.module.company.dto;

import java.time.ZonedDateTime;

import digital.ah.avanza.sgc.module.master.entity.CatalogItemEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CompanyPaginationDTO {

    private Long id;

    private CatalogItemEntity country;

    private String businessName;

    private String taxId;

    private String city;

    private String phone;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    private Boolean status;

}
