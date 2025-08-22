package digital.ah.avanza.sgc.module.product.repository.brand;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import digital.ah.avanza.sgc.module.product.entity.brand.BrandEntity;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    Optional<BrandEntity> findByCompanyIdAndName(Long companyId, String name);

    Page<BrandEntity> findByCompanyId(Pageable pageable, Long companyId);

}
