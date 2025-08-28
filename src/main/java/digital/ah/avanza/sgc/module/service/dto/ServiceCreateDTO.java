package digital.ah.avanza.sgc.module.service.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ServiceCreateDTO {

    private Long company;

    private String internal_code;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer durationMinutes;

    private Integer warrantyMonths;

}
