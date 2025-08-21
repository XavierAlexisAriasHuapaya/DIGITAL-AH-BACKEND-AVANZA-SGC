package digital.ah.avanza.sgc.module.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import digital.ah.avanza.sgc.module.master.entity.MasterCatalogEntity;

@Repository
public interface MasterCatalogRepository extends JpaRepository<MasterCatalogEntity, Long> {

}
