package digital.ah.avanza.sgc.module.service.dto;

import java.math.BigDecimal;

import digital.ah.avanza.sgc.module.service.entity.ServiceEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ServicePaginationDTO {

    private Long id;

    private String internal_code;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer durationMinutes;

    private Integer warrantyMonths;

    public ServicePaginationDTO(ServiceEntity service) {
        this.id = service.getId();
        this.internal_code = service.getInternal_code();
        this.name = service.getName();
        this.description = service.getDescription();
        this.price = service.getPrice();
        this.durationMinutes = service.getDurationMinutes();
        this.warrantyMonths = service.getWarrantyMonths();
    }

}
