package digital.ah.avanza.sgc.module.employee.entity;

import java.time.ZonedDateTime;

import digital.ah.avanza.sgc.module.company.entity.CompanyEntity;
import digital.ah.avanza.sgc.module.master.entity.CatalogItemEntity;
import digital.ah.avanza.sgc.module.user.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "company_id")
    @ManyToOne
    private CompanyEntity company;

    @JoinColumn(name = "document_type_id")
    @ManyToOne
    private CatalogItemEntity documentType;

    @JoinColumn(name = "user_id", nullable = true)
    @OneToOne
    private UserEntity user;

    private String firstName;

    private String lastName;

    private String identityDocumentNumber;

    private String address;

    private String city;

    private String phone;

    private String email;

    @Column(updatable = false)
    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    private Boolean status;

    @PrePersist
    private void prePersist() {
        this.createdAt = ZonedDateTime.now();
        this.updatedAt = ZonedDateTime.now();
        this.status = true;
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = ZonedDateTime.now();
    }

}
