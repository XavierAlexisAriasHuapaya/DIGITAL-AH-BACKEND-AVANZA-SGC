package digital.ah.avanza.sgc.module.product.interfaces;

import org.springframework.data.domain.Pageable;

import digital.ah.avanza.sgc.module.product.dto.ProductCreateDTO;
import digital.ah.avanza.sgc.module.product.dto.ProductOneDTO;
import digital.ah.avanza.sgc.module.product.dto.ProductPaginationDTO;
import digital.ah.avanza.sgc.module.product.dto.ProductUpdateDTO;
import digital.ah.avanza.sgc.utils.model.PageDTO;

public interface ProductImplement {
    String create(ProductCreateDTO product);

    String update(ProductUpdateDTO product);

    ProductOneDTO findOne(Long id);

    PageDTO<ProductPaginationDTO> pagination(Pageable pageable, Long companyId);
}
