package digital.ah.avanza.sgc.module.company.entity;

import java.time.ZonedDateTime;

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
@Table(name = "companies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "country_id", nullable = true)
    @ManyToOne
    private CatalogItemEntity country;

    private String businessName;

    private String tradeName;

    private String taxId;

    private String address;

    private String city;

    private String phone;

    private String email;

    private String web;

    @Column(updatable = false)
    private ZonedDateTime created_at;

    private ZonedDateTime updated_at;

    private Boolean status;

    @PrePersist
    public void prePersist() {
        this.created_at = ZonedDateTime.now();
        this.updated_at = ZonedDateTime.now();
        this.status = true;
    }

    @PreUpdate
    public void preUpdate() {
        this.updated_at = ZonedDateTime.now();
    }

}
