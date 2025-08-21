package digital.ah.avanza.sgc.module.employee.dto;

import digital.ah.avanza.sgc.module.user.dto.UserCreateDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeCreateDTO {

    private Long company;

    private Long documentType;

    private UserCreateDTO user;

    private String firstName;

    private String lastName;

    private String identityDocumentNumber;

    private String address;

    private String city;

    private String phone;

    private String email;

}
