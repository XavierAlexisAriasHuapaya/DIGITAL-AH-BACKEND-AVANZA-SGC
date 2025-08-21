package digital.ah.avanza.sgc.module.company.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import digital.ah.avanza.sgc.module.company.entity.CompanyEntity;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    public Optional<CompanyEntity> findByCountry_IdAndTaxId(Long countryId, String taxId);

}
