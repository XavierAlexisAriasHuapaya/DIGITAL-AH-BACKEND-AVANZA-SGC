package digital.ah.avanza.sgc.module.product.service.category;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import digital.ah.avanza.sgc.module.product.dto.category.CategoryCreateDTO;
import digital.ah.avanza.sgc.module.product.dto.category.CategoryOneDTO;
import digital.ah.avanza.sgc.module.product.dto.category.CategoryPaginationDTO;
import digital.ah.avanza.sgc.module.product.dto.category.CategoryUpdateDTO;
import digital.ah.avanza.sgc.module.product.entity.category.CategoryEntity;
import digital.ah.avanza.sgc.module.product.interfaces.category.CategoryImplement;
import digital.ah.avanza.sgc.module.product.repository.category.CategoryRepository;
import digital.ah.avanza.sgc.module.company.dto.CompanyOneDTO;
import digital.ah.avanza.sgc.module.company.entity.CompanyEntity;
import digital.ah.avanza.sgc.module.company.service.CompanyService;
import digital.ah.avanza.sgc.utils.exception.MessageException;
import digital.ah.avanza.sgc.utils.model.PageDTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoryService implements CategoryImplement {

    private final CategoryRepository categoryRepository;

    private final CompanyService companyService;

    private Boolean categoryExists(Long companyId, String name) {
        return this.categoryRepository
                .findByCompanyIdAndName(companyId, name).isPresent();
    }

    @Transactional
    @Override
    public String create(CategoryCreateDTO category) {
        if (this.categoryExists(category.getCompany(), category.getName())) {
            throw new MessageException("Category allready exists");
        }

        CompanyOneDTO companyDTO = this.companyService.findOne(category.getCompany());
        CompanyEntity companyEntity = CompanyEntity
                .builder().id(companyDTO.getId()).build();

        if (this.categoryExists(category.getCompany(), category.getName())) {
            throw new MessageException("Category already exists");
        }

        CategoryEntity categoryEntity = CategoryEntity.builder()
                .company(companyEntity)
                .name(category.getName())
                .description(category.getDescription())
                .build();
        return this.categoryRepository.save(categoryEntity).getId() != 0 ? "Successfully Created"
                : "Error Creating Category";
    }

    @Transactional
    @Override
    public String update(CategoryUpdateDTO category) {
        Optional<CategoryEntity> categoryOpt = this.categoryRepository.findById(category.getId());
        if (!categoryOpt.isPresent()) {
            throw new MessageException("Category not found");
        }
        CategoryEntity categoryEntity = categoryOpt.get();

        if (!category.getName().equals(categoryEntity.getName())) {
            if (this.categoryExists(categoryEntity.getCompany().getId(), category.getName())) {
                throw new MessageException("Category already exists");
            }
        }

        categoryEntity.setName(category.getName());
        categoryEntity.setDescription(category.getDescription());
        this.categoryRepository.save(categoryEntity);
        return "Successfully Updated";
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryOneDTO findOne(Long id) {
        Optional<CategoryEntity> categoryOpt = this.categoryRepository.findById(id);
        if (!categoryOpt.isPresent()) {
            throw new MessageException("Category not found");
        }
        CategoryEntity categoryEntity = categoryOpt.get();
        return new CategoryOneDTO(categoryEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public PageDTO<CategoryPaginationDTO> pagination(Pageable pageable, Long companyId) {
        Page<CategoryEntity> page = this.categoryRepository.findByCompanyId(pageable, companyId);
        List<CategoryPaginationDTO> content = page.getContent().stream()
                .map(category -> new CategoryPaginationDTO(category)).toList();
        return new PageDTO<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
    }

}
