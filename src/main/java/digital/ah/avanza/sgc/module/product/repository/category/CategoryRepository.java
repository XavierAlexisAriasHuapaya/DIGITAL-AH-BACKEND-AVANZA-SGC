package digital.ah.avanza.sgc.module.product.repository.category;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import digital.ah.avanza.sgc.module.product.entity.category.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByCompanyIdAndName(Long companyId, String name);

    Page<CategoryEntity> findByCompanyId(Pageable pageable, Long companyId);

}
