package digital.ah.avanza.sgc.module.rol.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import digital.ah.avanza.sgc.module.company.service.CompanyService;
import digital.ah.avanza.sgc.module.rol.dto.RolAllDTO;
import digital.ah.avanza.sgc.module.rol.dto.RolOneDTO;
import digital.ah.avanza.sgc.module.rol.interfaces.RolImplement;
import digital.ah.avanza.sgc.module.rol.repository.RolRepository;
import digital.ah.avanza.sgc.utils.exception.MessageException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RolService implements RolImplement {

    private final RolRepository rolRepository;

    private final CompanyService companyService;

    @Transactional(readOnly = true)
    @Override
    public RolOneDTO findOne(Long id) {
        return this.rolRepository.findById(id).map(rol -> new RolOneDTO(rol))
                .orElseThrow(() -> new MessageException("Rol not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<RolAllDTO> getAll(Long companyId) {
        this.companyService.findOne(companyId);
        return this.rolRepository.findByCompany_Id(companyId).stream()
                .map(rol -> new RolAllDTO(rol))
                .toList();
    }

}
