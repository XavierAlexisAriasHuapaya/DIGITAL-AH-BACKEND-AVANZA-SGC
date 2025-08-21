package digital.ah.avanza.sgc.module.branch.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import digital.ah.avanza.sgc.module.branch.entity.BranchEntity;

@Repository
public interface BranchRepository extends JpaRepository<BranchEntity, Long> {

    Optional<BranchEntity> findByCompanyIdAndName(Long companyId, String name);

    Page<BranchEntity> findByCompanyId(Pageable pageable, Long companyId);

}
