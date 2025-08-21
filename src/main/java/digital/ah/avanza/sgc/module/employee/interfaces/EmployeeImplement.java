package digital.ah.avanza.sgc.module.employee.interfaces;

import org.springframework.data.domain.Pageable;

import digital.ah.avanza.sgc.module.employee.dto.EmployeeCreateDTO;
import digital.ah.avanza.sgc.module.employee.dto.EmployeeOneDTO;
import digital.ah.avanza.sgc.module.employee.dto.EmployeePaginationDTO;
import digital.ah.avanza.sgc.module.employee.dto.EmployeeUpdateDTO;
import digital.ah.avanza.sgc.utils.model.PageDTO;

public interface EmployeeImplement {

    String create(EmployeeCreateDTO employee);

    String update(EmployeeUpdateDTO employee);

    EmployeeOneDTO findOne(Long id);

    PageDTO<EmployeePaginationDTO> pagination(Pageable pageable, Long companyId);

}
