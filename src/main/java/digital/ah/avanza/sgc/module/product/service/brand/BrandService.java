package digital.ah.avanza.sgc.module.product.service.brand;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import digital.ah.avanza.sgc.module.product.dto.brand.BrandCreateDTO;
import digital.ah.avanza.sgc.module.product.dto.brand.BrandOneDTO;
import digital.ah.avanza.sgc.module.product.dto.brand.BrandPaginationDTO;
import digital.ah.avanza.sgc.module.product.dto.brand.BrandUpdateDTO;
import digital.ah.avanza.sgc.module.product.entity.brand.BrandEntity;
import digital.ah.avanza.sgc.module.product.interfaces.brand.BrandImplement;
import digital.ah.avanza.sgc.module.product.repository.brand.BrandRepository;
import digital.ah.avanza.sgc.module.company.dto.CompanyOneDTO;
import digital.ah.avanza.sgc.module.company.entity.CompanyEntity;
import digital.ah.avanza.sgc.module.company.service.CompanyService;
import digital.ah.avanza.sgc.utils.exception.MessageException;
import digital.ah.avanza.sgc.utils.model.PageDTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BrandService implements BrandImplement {

    private final BrandRepository brandRepository;

    private final CompanyService companyService;

    private Boolean brandExists(Long companyId, String name) {
        return this.brandRepository
                .findByCompanyIdAndName(companyId, name).isPresent();
    }

    @Transactional
    @Override
    public String create(BrandCreateDTO brand) {
        if (this.brandExists(brand.getCompany(), brand.getName())) {
            throw new MessageException("Brand already exists");
        }

        CompanyOneDTO companyDTO = this.companyService.findOne(brand.getCompany());
        CompanyEntity companyEntity = CompanyEntity
                .builder().id(companyDTO.getId()).build();

        BrandEntity brandEntity = BrandEntity.builder()
                .company(companyEntity)
                .name(brand.getName())
                .description(brand.getDescription())
                .build();
        return this.brandRepository.save(brandEntity).getId() != 0 ? "Successfully Created"
                : "Error Creating Brand";
    }

    @Transactional
    @Override
    public String update(BrandUpdateDTO brand) {
        Optional<BrandEntity> brandOpt = this.brandRepository.findById(brand.getId());
        if (!brandOpt.isPresent()) {
            throw new MessageException("Brand not found");
        }
        BrandEntity brandEntity = brandOpt.get();

        if (!brand.getName().equals(brandEntity.getName())) {
            if (this.brandExists(brandEntity.getCompany().getId(), brand.getName())) {
                throw new MessageException("Brand already exists");
            }
        }

        brandEntity.setName(brand.getName());
        brandEntity.setDescription(brand.getDescription());
        this.brandRepository.save(brandEntity);
        return "Successfully Updated";
    }

    @Transactional(readOnly = true)
    @Override
    public BrandOneDTO findOne(Long id) {
        Optional<BrandEntity> brandOpt = this.brandRepository.findById(id);
        if (!brandOpt.isPresent()) {
            throw new MessageException("Brand not found");
        }
        BrandEntity brandEntity = brandOpt.get();
        return new BrandOneDTO(brandEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public PageDTO<BrandPaginationDTO> pagination(Pageable pageable, Long companyId) {
        Page<BrandEntity> page = this.brandRepository.findByCompanyId(pageable, companyId);
        List<BrandPaginationDTO> content = page.getContent().stream()
                .map(brand -> new BrandPaginationDTO(brand)).toList();
        return new PageDTO<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
    }

}
