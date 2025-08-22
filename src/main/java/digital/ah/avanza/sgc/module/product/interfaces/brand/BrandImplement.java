package digital.ah.avanza.sgc.module.product.interfaces.brand;

import org.springframework.data.domain.Pageable;

import digital.ah.avanza.sgc.module.product.dto.brand.BrandCreateDTO;
import digital.ah.avanza.sgc.module.product.dto.brand.BrandOneDTO;
import digital.ah.avanza.sgc.module.product.dto.brand.BrandPaginationDTO;
import digital.ah.avanza.sgc.module.product.dto.brand.BrandUpdateDTO;
import digital.ah.avanza.sgc.utils.model.PageDTO;

public interface BrandImplement {

    String create(BrandCreateDTO brand);

    String update(BrandUpdateDTO brand);

    BrandOneDTO findOne(Long id);

    PageDTO<BrandPaginationDTO> pagination(Pageable pageable, Long companyId);

}
