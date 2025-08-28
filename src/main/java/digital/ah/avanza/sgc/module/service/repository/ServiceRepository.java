package digital.ah.avanza.sgc.module.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import digital.ah.avanza.sgc.module.service.entity.ServiceEntity;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {

    Boolean existsByCompanyIdAndName(Long companyId, String name);

    Page<ServiceEntity> findByCompanyId(Pageable pageable, Long companyId);

}
