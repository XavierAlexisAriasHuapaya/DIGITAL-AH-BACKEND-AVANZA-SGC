package digital.ah.avanza.sgc.module.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CompanyCreateDTO {

    private Long countryId;

    private String businessName;

    private String tradeName;

    private String taxId;

    private String address;

    private String city;

    private String phone;

    private String email;

    private String web;

}
