package digital.ah.avanza.sgc.module.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import digital.ah.avanza.sgc.module.product.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Boolean existsByCompanyIdAndBarCode(Long companyId, String barCode);

    Page<ProductEntity> findByCompanyId(Pageable pageable, Long companyId);

}
