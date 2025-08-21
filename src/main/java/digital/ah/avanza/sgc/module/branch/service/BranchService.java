package digital.ah.avanza.sgc.module.branch.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import digital.ah.avanza.sgc.module.branch.dto.BranchCreateDTO;
import digital.ah.avanza.sgc.module.branch.dto.BranchOneDTO;
import digital.ah.avanza.sgc.module.branch.dto.BranchPaginationDTO;
import digital.ah.avanza.sgc.module.branch.dto.BranchUpdateDTO;
import digital.ah.avanza.sgc.module.branch.entity.BranchEntity;
import digital.ah.avanza.sgc.module.branch.interfaces.BranchImplement;
import digital.ah.avanza.sgc.module.branch.repository.BranchRepository;
import digital.ah.avanza.sgc.module.company.dto.CompanyOneDTO;
import digital.ah.avanza.sgc.module.company.entity.CompanyEntity;
import digital.ah.avanza.sgc.module.company.service.CompanyService;
import digital.ah.avanza.sgc.module.master.dto.CatalogItemOneDTO;
import digital.ah.avanza.sgc.module.master.entity.CatalogItemEntity;
import digital.ah.avanza.sgc.module.master.services.CatalogItemService;
import digital.ah.avanza.sgc.utils.exception.MessageException;
import digital.ah.avanza.sgc.utils.model.PageDTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BranchService implements BranchImplement {

    private final BranchRepository branchRepository;

    private final CompanyService companyService;

    private final CatalogItemService catalogItemService;

    @Transactional
    @Override
    public String create(BranchCreateDTO branch) {
        CompanyOneDTO companyDTO = this.companyService.findOne(branch.getCompany());
        CompanyEntity companyEntity = CompanyEntity
                .builder().id(companyDTO.getId()).build();
        Optional<BranchEntity> branchOpt = this.branchRepository.findByCompanyIdAndName(companyEntity.getId(),
                branch.getName());
        if (branchOpt.isPresent()) {
            throw new MessageException("Branch with name " + branch.getName() + " already exists");
        }

        CatalogItemOneDTO catalogItemDTO = this.catalogItemService.findOne(branch.getCurrency());
        CatalogItemEntity catalogItemEntity = CatalogItemEntity
                .builder().id(catalogItemDTO.getId()).build();

        BranchEntity branchEntity = BranchEntity.builder()
                .company(companyEntity)
                .currency(catalogItemEntity)
                .name(branch.getName())
                .address(branch.getAddress())
                .city(branch.getCity())
                .phone(branch.getPhone())
                .image(branch.getImage())
                .build();
        branchEntity = this.branchRepository.save(branchEntity);
        return branchEntity.getId() != 0 ? "Successfully Created" : "Error Creating Branch";
    }

    @Transactional
    @Override
    public String update(BranchUpdateDTO branch) {
        Optional<BranchEntity> branchOpt = this.branchRepository.findById(branch.getId());
        if (branchOpt.isEmpty()) {
            throw new MessageException("Branch not found");
        }
        BranchEntity branchEntity = branchOpt.get();

        if (!branch.getName().equals(branchEntity.getName())) {
            Optional<BranchEntity> branchExists = this.branchRepository.findByCompanyIdAndName(
                    branchEntity.getCompany().getId(),
                    branch.getName());
            if (branchExists.isPresent()) {
                throw new MessageException("Branch with name " + branch.getName() + " already exists");
            }
        }

        CatalogItemOneDTO catalogItemDTO = this.catalogItemService.findOne(branch.getCurrency());
        CatalogItemEntity catalogItemEntity = CatalogItemEntity
                .builder().id(catalogItemDTO.getId()).build();

        branchEntity.setCurrency(catalogItemEntity);
        branchEntity.setName(branch.getName());
        branchEntity.setAddress(branch.getAddress());
        branchEntity.setCity(branch.getCity());
        branchEntity.setPhone(branch.getPhone());
        branchEntity.setImage(branch.getImage());
        branchEntity = this.branchRepository.save(branchEntity);
        return branchEntity.getId() != 0 ? "Successfully Updated" : "Error Updating Branch";
    }

    @Transactional(readOnly = true)
    @Override
    public BranchOneDTO findOne(Long id) {
        Optional<BranchEntity> branchOpt = this.branchRepository.findById(id);
        if (branchOpt.isEmpty()) {
            throw new MessageException("Branch not found");
        }
        return new BranchOneDTO(branchOpt.get());
    }

    @Transactional(readOnly = true)
    @Override
    public PageDTO<BranchPaginationDTO> pagination(Pageable pageable, Long companyId, String search) {
        Page<BranchEntity> branchPage = this.branchRepository.findByCompanyId(pageable, companyId);
        List<BranchPaginationDTO> dto = branchPage.getContent().stream()
                .map(branch -> new BranchPaginationDTO(branch)).toList();
        return new PageDTO<>(dto, branchPage.getNumber(), branchPage.getSize(), branchPage.getTotalElements());
    }

    @Override
    public List<BranchEntity> findAllByIds(Set<Long> ids) {
        return branchRepository.findAllById(ids);
    }

}
