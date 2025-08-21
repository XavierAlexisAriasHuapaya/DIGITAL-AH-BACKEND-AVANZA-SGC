package digital.ah.avanza.sgc.module.rol.interfaces;

import java.util.List;

import digital.ah.avanza.sgc.module.rol.dto.RolAllDTO;
import digital.ah.avanza.sgc.module.rol.dto.RolOneDTO;

public interface RolImplement {

    RolOneDTO findOne(Long id);

    List<RolAllDTO> getAll(Long companyId);
}
