package digital.ah.avanza.sgc.module.user.dto;

import digital.ah.avanza.sgc.module.rol.entity.RolEntity;
import digital.ah.avanza.sgc.module.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserOneDTO {

    private Long id;

    private RolEntity rol;

    private String username;

    private String password;

    public UserOneDTO(UserEntity user) {
        this.id = user.getId();
        this.rol = user.getRol();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

}
