package digital.ah.avanza.sgc.module.currency.entity;

import java.time.ZonedDateTime;

import digital.ah.avanza.sgc.module.company.entity.CompanyEntity;
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

@Table(name = "currencies")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CurrencyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "company_id")
    @ManyToOne
    private CompanyEntity company;

    private String code;

    private String name;

    private String symbol;

    @Column(updatable = false)
    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    private Boolean status;

    @PrePersist
    public void prePersist() {
        this.createdAt = ZonedDateTime.now();
        this.updatedAt = ZonedDateTime.now();
        this.status = true;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = ZonedDateTime.now();
    }

}
