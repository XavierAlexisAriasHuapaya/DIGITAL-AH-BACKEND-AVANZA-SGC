package digital.ah.avanza.sgc.module.product.interfaces.category;

import org.springframework.data.domain.Pageable;

import digital.ah.avanza.sgc.module.product.dto.category.CategoryCreateDTO;
import digital.ah.avanza.sgc.module.product.dto.category.CategoryOneDTO;
import digital.ah.avanza.sgc.module.product.dto.category.CategoryPaginationDTO;
import digital.ah.avanza.sgc.module.product.dto.category.CategoryUpdateDTO;
import digital.ah.avanza.sgc.utils.model.PageDTO;

public interface CategoryImplement {

    String create(CategoryCreateDTO category);

    String update(CategoryUpdateDTO category);

    CategoryOneDTO findOne(Long id);

    PageDTO<CategoryPaginationDTO> pagination(Pageable pageable, Long companyId);

}
