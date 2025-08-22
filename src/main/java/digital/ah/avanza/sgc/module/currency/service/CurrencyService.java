package digital.ah.avanza.sgc.module.currency.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import digital.ah.avanza.sgc.module.company.dto.CompanyOneDTO;
import digital.ah.avanza.sgc.module.company.entity.CompanyEntity;
import digital.ah.avanza.sgc.module.company.service.CompanyService;
import digital.ah.avanza.sgc.module.currency.dto.CurrencyCreateDTO;
import digital.ah.avanza.sgc.module.currency.dto.CurrencyOneDTO;
import digital.ah.avanza.sgc.module.currency.dto.CurrencyPaginationDTO;
import digital.ah.avanza.sgc.module.currency.dto.CurrencyUpdateDTO;
import digital.ah.avanza.sgc.module.currency.entity.CurrencyEntity;
import digital.ah.avanza.sgc.module.currency.interfaces.CurrencyImplement;
import digital.ah.avanza.sgc.module.currency.repository.CurrencyRepository;
import digital.ah.avanza.sgc.utils.exception.MessageException;
import digital.ah.avanza.sgc.utils.model.PageDTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CurrencyService implements CurrencyImplement {

    private final CurrencyRepository currencyRepository;

    private final CompanyService companyService;

    private Boolean currencyExists(Long companyId, String name, String code, String symbol) {
        return this.currencyRepository
                .findByCompanyIdAndNameAndCodeAndSymbol(companyId, name, code, symbol).isPresent();
    }

    @Override
    public String create(CurrencyCreateDTO currency) {
        CompanyOneDTO companyDTO = this.companyService.findOne(currency.getCompany());
        CompanyEntity companyEntity = CompanyEntity
                .builder().id(companyDTO.getId()).build();

        if (this.currencyExists(companyDTO.getId(), currency.getName(), currency.getCode(), currency.getSymbol())) {
            throw new MessageException("Currency Already Exists");
        }

        CurrencyEntity currencyEntity = CurrencyEntity.builder()
                .company(companyEntity)
                .code(currency.getCode())
                .name(currency.getName())
                .symbol(currency.getSymbol())
                .build();
        return this.currencyRepository.save(currencyEntity).getId() != 0 ? "Successfully Created"
                : "Error Creating Currency";
    }

    @Override
    public String update(CurrencyUpdateDTO currency) {
        Optional<CurrencyEntity> currencyOpt = this.currencyRepository.findById(currency.getId());
        if (!currencyOpt.isPresent()) {
            throw new MessageException("Currency Not Found");
        }
        CurrencyEntity currencyEntity = currencyOpt.get();
        if (!currencyEntity.getName().equals(currency.getName()) && !currencyEntity.getCode().equals(currency.getCode())
                && !currencyEntity.getSymbol().equals(currency.getSymbol())) {
            if (this.currencyExists(currencyEntity.getCompany().getId(), currency.getName(), currency.getCode(),
                    currency.getSymbol())) {
                throw new MessageException("Currency Already Exists");
            }
        }

        currencyEntity.setCode(currency.getCode());
        currencyEntity.setName(currency.getName());
        currencyEntity.setSymbol(currency.getSymbol());
        this.currencyRepository.save(currencyEntity);
        return "Successfully Updated";
    }

    @Override
    public CurrencyOneDTO findOne(Long id) {
        Optional<CurrencyEntity> currencyOpt = this.currencyRepository.findById(id);
        if (!currencyOpt.isPresent()) {
            throw new MessageException("Currency Not Found");
        }
        return new CurrencyOneDTO(currencyOpt.get());
    }

    @Override
    public PageDTO<CurrencyPaginationDTO> pagination(Pageable pageable, Long companyId) {
        Page<CurrencyEntity> currencyPage = this.currencyRepository.findByCompanyId(pageable, companyId);
        List<CurrencyPaginationDTO> dto = currencyPage.getContent().stream()
                .map(currency -> new CurrencyPaginationDTO(currency)).toList();
        return new PageDTO<>(dto, currencyPage.getNumber(), currencyPage.getSize(), currencyPage.getTotalElements());
    }

}
