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
public class CompanyOneDTO {

    private Long id;

    private CatalogItemEntity country;

    private String businessName;

    private String tradeName;

    private String taxId;

    private String address;

    private String city;

    private String phone;

    private String email;

    private String web;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    private Boolean status;

}
