package digital.ah.avanza.sgc.module.company.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

import digital.ah.avanza.sgc.module.company.dto.CompanyCreateDTO;
import digital.ah.avanza.sgc.module.company.dto.CompanyOneDTO;
import digital.ah.avanza.sgc.module.company.dto.CompanyPaginationDTO;
import digital.ah.avanza.sgc.module.company.dto.CompanyUpdateDTO;
import digital.ah.avanza.sgc.module.company.entity.CompanyEntity;
import digital.ah.avanza.sgc.utils.model.PageDTO;

public interface CompanyImplement {

    String create(CompanyCreateDTO company);

    String update(CompanyUpdateDTO company);

    CompanyOneDTO findOne(Long id);

    PageDTO<CompanyPaginationDTO> pagination(Pageable pageable, String search);

    Optional<CompanyEntity> findByCountry_IdAndTaxId(Long countryId, String taxId);

}
