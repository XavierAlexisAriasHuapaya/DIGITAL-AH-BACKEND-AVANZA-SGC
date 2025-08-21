package digital.ah.avanza.sgc.module.branch.entity;

import java.time.ZonedDateTime;

import digital.ah.avanza.sgc.module.company.entity.CompanyEntity;
import digital.ah.avanza.sgc.module.master.entity.CatalogItemEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "branches")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BranchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private CatalogItemEntity currency;

    private String name;

    private String address;

    private String city;

    private String phone;

    private String image;

    @Column(updatable = false)
    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    private Boolean status;

    @PrePersist
    private void prePersist() {
        createdAt = ZonedDateTime.now();
        updatedAt = ZonedDateTime.now();
        this.status = true;
    }

    @PreUpdate
    private void preUpdate() {
        updatedAt = ZonedDateTime.now();
    }

}
