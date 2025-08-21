package digital.ah.avanza.sgc.module.branch.interfaces;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;

import digital.ah.avanza.sgc.module.branch.dto.BranchCreateDTO;
import digital.ah.avanza.sgc.module.branch.dto.BranchOneDTO;
import digital.ah.avanza.sgc.module.branch.dto.BranchPaginationDTO;
import digital.ah.avanza.sgc.module.branch.dto.BranchUpdateDTO;
import digital.ah.avanza.sgc.module.branch.entity.BranchEntity;
import digital.ah.avanza.sgc.utils.model.PageDTO;

public interface BranchImplement {

    String create(BranchCreateDTO branch);

    String update(BranchUpdateDTO branch);

    BranchOneDTO findOne(Long id);

    PageDTO<BranchPaginationDTO> pagination(Pageable pageable, Long companyId, String search);
    
    List<BranchEntity> findAllByIds(Set<Long> ids);

}
