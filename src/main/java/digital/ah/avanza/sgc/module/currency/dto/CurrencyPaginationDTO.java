package digital.ah.avanza.sgc.module.currency.dto;

import digital.ah.avanza.sgc.module.currency.entity.CurrencyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CurrencyPaginationDTO {
    private Long id;

    private String code;

    private String name;

    private String symbol;

    private Boolean status;

    public CurrencyPaginationDTO(CurrencyEntity currency) {
        this.id = currency.getId();
        this.code = currency.getCode();
        this.name = currency.getName();
        this.symbol = currency.getSymbol();
        this.status = currency.getStatus();
    }
}
