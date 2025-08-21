package digital.ah.avanza.sgc.module.employee.dto;

import digital.ah.avanza.sgc.module.employee.entity.EmployeeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeOneDTO {

    private Long id;

    private String documentType;

    private String firstName;

    private String lastName;

    private String identityDocumentNumber;

    private String city;

    private String phone;

    private Boolean status;

    public EmployeeOneDTO(EmployeeEntity employee) {
        this.id = employee.getId();
        this.documentType = employee.getDocumentType().getName();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.identityDocumentNumber = employee.getIdentityDocumentNumber();
        this.city = employee.getCity();
        this.phone = employee.getPhone();
        this.status = employee.getStatus();
    }

}
