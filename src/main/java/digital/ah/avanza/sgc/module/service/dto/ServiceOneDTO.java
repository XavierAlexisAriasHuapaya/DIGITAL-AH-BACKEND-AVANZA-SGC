package digital.ah.avanza.sgc.module.service.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import digital.ah.avanza.sgc.module.service.entity.ServiceEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ServiceOneDTO {

    private Long id;

    private String internal_code;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer durationMinutes;

    private Integer warrantyMonths;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    private Boolean status;

    public ServiceOneDTO(ServiceEntity service) {
        this.id = service.getId();
        this.internal_code = service.getInternal_code();
        this.name = service.getName();
        this.description = service.getDescription();
        this.price = service.getPrice();
        this.durationMinutes = service.getDurationMinutes();
        this.warrantyMonths = service.getWarrantyMonths();
        this.createdAt = service.getCreatedAt();
        this.updatedAt = service.getUpdatedAt();
        this.status = service.getStatus();
    }

}
