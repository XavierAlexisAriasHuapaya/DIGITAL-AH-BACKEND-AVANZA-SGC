package digital.ah.avanza.sgc.module.currency.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CurrencyCreateDTO {

    private Long company;

    private String code;

    private String name;

    private String symbol;

}
