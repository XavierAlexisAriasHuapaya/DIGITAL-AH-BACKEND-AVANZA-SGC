package digital.ah.avanza.sgc.module.currency.interfaces;

import org.springframework.data.domain.Pageable;

import digital.ah.avanza.sgc.module.currency.dto.CurrencyCreateDTO;
import digital.ah.avanza.sgc.module.currency.dto.CurrencyOneDTO;
import digital.ah.avanza.sgc.module.currency.dto.CurrencyPaginationDTO;
import digital.ah.avanza.sgc.module.currency.dto.CurrencyUpdateDTO;
import digital.ah.avanza.sgc.utils.model.PageDTO;

public interface CurrencyImplement {

    String create(CurrencyCreateDTO currency);

    String update(CurrencyUpdateDTO currency);

    CurrencyOneDTO findOne(Long id);

    PageDTO<CurrencyPaginationDTO> pagination(Pageable pageable, Long companyId);
}
