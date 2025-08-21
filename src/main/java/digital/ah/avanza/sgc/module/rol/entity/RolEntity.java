package digital.ah.avanza.sgc.module.rol.entity;

import java.time.ZonedDateTime;

import digital.ah.avanza.sgc.module.company.entity.CompanyEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "company_id")
    @ManyToOne
    private CompanyEntity company;

    private String name;

    private String description;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    private Boolean status;

}
