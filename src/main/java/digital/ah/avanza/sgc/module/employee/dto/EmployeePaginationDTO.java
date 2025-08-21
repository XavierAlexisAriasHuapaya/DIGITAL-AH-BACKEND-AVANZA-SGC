package digital.ah.avanza.sgc.module.employee.dto;

import java.time.ZonedDateTime;

import digital.ah.avanza.sgc.module.employee.entity.EmployeeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeePaginationDTO {

    private Long id;

    private String fullName;

    private String identityDocumentNumber;

    private String city;

    private String phone;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    private Boolean status;

    public EmployeePaginationDTO(EmployeeEntity employee) {
        this.id = employee.getId();
        this.fullName = employee.getFirstName() + " " + employee.getLastName();
        this.identityDocumentNumber = employee.getIdentityDocumentNumber();
        this.city = employee.getCity();
        this.phone = employee.getPhone();
        this.createdAt = employee.getCreatedAt();
        this.updatedAt = employee.getUpdatedAt();
        this.status = employee.getStatus();
    }

}
