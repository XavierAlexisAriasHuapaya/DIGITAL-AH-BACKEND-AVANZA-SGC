package digital.ah.avanza.sgc.module.service.interfaces;

import org.springframework.data.domain.Pageable;

import digital.ah.avanza.sgc.module.service.dto.ServiceCreateDTO;
import digital.ah.avanza.sgc.module.service.dto.ServiceOneDTO;
import digital.ah.avanza.sgc.module.service.dto.ServicePaginationDTO;
import digital.ah.avanza.sgc.module.service.dto.ServiceUpdateDTO;
import digital.ah.avanza.sgc.utils.model.PageDTO;

public interface ServiceImplement {

    String create(ServiceCreateDTO service);

    String update(ServiceUpdateDTO service);

    ServiceOneDTO findOne(Long id);

    PageDTO<ServicePaginationDTO> pagination(Pageable pageable, Long companyId, String search);

}
