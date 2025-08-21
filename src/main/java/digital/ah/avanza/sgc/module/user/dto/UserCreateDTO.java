package digital.ah.avanza.sgc.module.user.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateDTO {

    private Long rol;

    private String username;

    private String password;

    private Set<Long> branches;

}
