package digital.ah.avanza.sgc.module.rol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import digital.ah.avanza.sgc.module.rol.entity.RolEntity;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, Long> {

    List<RolEntity> findByCompany_Id(Long companyId);

}
