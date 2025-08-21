package digital.ah.avanza.sgc.module.company.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import digital.ah.avanza.sgc.module.company.dto.CompanyCreateDTO;
import digital.ah.avanza.sgc.module.company.dto.CompanyOneDTO;
import digital.ah.avanza.sgc.module.company.dto.CompanyPaginationDTO;
import digital.ah.avanza.sgc.module.company.dto.CompanyUpdateDTO;
import digital.ah.avanza.sgc.module.company.entity.CompanyEntity;
import digital.ah.avanza.sgc.module.company.interfaces.CompanyImplement;
import digital.ah.avanza.sgc.module.company.repository.CompanyRepository;
import digital.ah.avanza.sgc.module.master.dto.CatalogItemOneDTO;
import digital.ah.avanza.sgc.module.master.entity.CatalogItemEntity;
import digital.ah.avanza.sgc.module.master.services.CatalogItemService;
import digital.ah.avanza.sgc.utils.exception.MessageException;
import digital.ah.avanza.sgc.utils.model.PageDTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CompanyService implements CompanyImplement {

    private final CompanyRepository companyRepository;

    private final CatalogItemService catalogItemService;

    @Transactional
    @Override
    public String create(CompanyCreateDTO company) {
        Optional<CatalogItemOneDTO> country = Optional.empty();
        CatalogItemEntity catalogItemEntity = null;
        if (company.getCountryId() != 0) {
            country = Optional.of(this.catalogItemService.findOne(company.getCountryId()));
            catalogItemEntity = CatalogItemEntity.builder()
                    .id(country.get().getId())
                    .build();
        }
        if (!this.findByCountry_IdAndTaxId(company.getCountryId(), company.getTaxId()).isEmpty()) {
            throw new MessageException("Company with the same country and tax ID already exists");
        }
        CompanyEntity entity = CompanyEntity.builder()
                .businessName(company.getBusinessName())
                .tradeName(company.getTradeName())
                .country(catalogItemEntity)
                .taxId(company.getTaxId())
                .address(company.getAddress())
                .city(company.getCity())
                .phone(company.getPhone())
                .email(company.getEmail())
                .web(company.getWeb())
                .build();
        companyRepository.save(entity);
        return "Successfully created";
    }

    @Transactional
    @Override
    public String update(CompanyUpdateDTO company) {
        Optional<CatalogItemOneDTO> country = Optional.empty();
        CatalogItemEntity catalogItemEntity = null;
        Optional<CompanyEntity> companyOpt = this.companyRepository.findById(company.getId());
        CompanyEntity companyEntity = null;

        if (companyOpt.isEmpty()) {
            throw new MessageException("Company not found");
        }
        companyEntity = companyOpt.get();
        if (company.getCountryId() != 0) {
            country = Optional.of(this.catalogItemService.findOne(company.getCountryId()));
            catalogItemEntity = CatalogItemEntity.builder()
                    .id(country.get().getId())
                    .build();
        }
        if (!company.getTaxId().equals(companyEntity.getTaxId())) {
            if (!this.findByCountry_IdAndTaxId(company.getCountryId(), company.getTaxId()).isEmpty()) {
                throw new MessageException("Company with the same country and tax ID already exists");
            }
        }

        companyEntity = CompanyEntity.builder()
                .id(company.getId())
                .businessName(company.getBusinessName())
                .tradeName(company.getTradeName())
                .country(catalogItemEntity)
                .taxId(company.getTaxId())
                .address(company.getAddress())
                .city(company.getCity())
                .phone(company.getPhone())
                .email(company.getEmail())
                .web(company.getWeb())
                .build();
        companyRepository.save(companyEntity);
        return "Successfully updated";
    }

    @Transactional(readOnly = true)
    @Override
    public CompanyOneDTO findOne(Long id) {
        Optional<CompanyEntity> companyOpt = this.companyRepository.findById(id);
        if (companyOpt.isEmpty()) {
            throw new MessageException("company not found");
        }
        CompanyEntity entity = companyOpt.get();
        CompanyOneDTO dto = CompanyOneDTO.builder()
                .id(id)
                .country(entity.getCountry())
                .businessName(entity.getBusinessName())
                .tradeName(entity.getTradeName())
                .taxId(entity.getTaxId())
                .address(entity.getAddress())
                .city(entity.getCity())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .web(entity.getWeb())
                .createdAt(entity.getCreated_at())
                .updatedAt(entity.getUpdated_at())
                .status(entity.getStatus())
                .build();
        return dto;
    }

    @Override
    public PageDTO<CompanyPaginationDTO> pagination(Pageable pageable, String search) {
        Page<CompanyEntity> companyPage = this.companyRepository.findAll(pageable);
        List<CompanyPaginationDTO> dto = companyPage.getContent().stream()
                .map(company -> new CompanyPaginationDTO(company.getId(), company.getCountry(),
                        company.getBusinessName(), company.getTaxId(), company.getCity(), company.getPhone(),
                        company.getCreated_at(), company.getUpdated_at(), company.getStatus()))
                .toList();
        return new PageDTO<>(dto, companyPage.getNumber(),
                companyPage.getSize(),
                companyPage.getTotalElements());
    }

    @Override
    public Optional<CompanyEntity> findByCountry_IdAndTaxId(Long countryId, String taxId) {
        Optional<CompanyEntity> companyOpt = this.companyRepository.findByCountry_IdAndTaxId(countryId, taxId);
        if (companyOpt.isEmpty()) {
            return Optional.empty();
        }
        return companyOpt;
    }
}
