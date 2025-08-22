package digital.ah.avanza.sgc.module.currency.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import digital.ah.avanza.sgc.module.currency.entity.CurrencyEntity;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Long> {

    Optional<CurrencyEntity> findByCompanyIdAndNameAndCodeAndSymbol(Long companyId, String name, String code,
            String symbol);

    Page<CurrencyEntity> findByCompanyId(Pageable pageable, Long companyId);

}
