package digital.ah.avanza.sgc.module.employee.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import digital.ah.avanza.sgc.module.employee.entity.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    Optional<EmployeeEntity> findByCompany_IdAndIdentityDocumentNumber(Long companyId, String identityDocumentNumber);

    Page<EmployeeEntity> findByCompanyId(Pageable pageable, Long companyId);

}
