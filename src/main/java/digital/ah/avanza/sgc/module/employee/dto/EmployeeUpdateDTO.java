package digital.ah.avanza.sgc.module.employee.dto;

import digital.ah.avanza.sgc.module.user.dto.UserUpdateDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeUpdateDTO {

    private Long id;

    private Long documentType;

    private UserUpdateDTO user;

    private String firstName;

    private String lastName;

    private String identityDocumentNumber;

    private String address;

    private String city;

    private String phone;

    private String email;

}
