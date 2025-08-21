package digital.ah.avanza.sgc.module.user.entity;

import java.util.Set;

import digital.ah.avanza.sgc.module.branch.entity.BranchEntity;
import digital.ah.avanza.sgc.module.rol.entity.RolEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "rol_id")
    @ManyToOne
    private RolEntity rol;

    @JoinTable(name = "users_branches", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "branch_id"))
    @ManyToMany
    private Set<BranchEntity> branches;

    private String username;

    private String password;

}
