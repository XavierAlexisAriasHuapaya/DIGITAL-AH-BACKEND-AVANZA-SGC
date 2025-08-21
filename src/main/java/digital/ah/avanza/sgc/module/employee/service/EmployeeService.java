package digital.ah.avanza.sgc.module.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import digital.ah.avanza.sgc.module.company.dto.CompanyOneDTO;
import digital.ah.avanza.sgc.module.company.entity.CompanyEntity;
import digital.ah.avanza.sgc.module.company.service.CompanyService;
import digital.ah.avanza.sgc.module.employee.dto.EmployeeCreateDTO;
import digital.ah.avanza.sgc.module.employee.dto.EmployeeOneDTO;
import digital.ah.avanza.sgc.module.employee.dto.EmployeePaginationDTO;
import digital.ah.avanza.sgc.module.employee.dto.EmployeeUpdateDTO;
import digital.ah.avanza.sgc.module.employee.entity.EmployeeEntity;
import digital.ah.avanza.sgc.module.employee.interfaces.EmployeeImplement;
import digital.ah.avanza.sgc.module.employee.repository.EmployeeRepository;
import digital.ah.avanza.sgc.module.master.dto.CatalogItemOneDTO;
import digital.ah.avanza.sgc.module.master.entity.CatalogItemEntity;
import digital.ah.avanza.sgc.module.master.services.CatalogItemService;
import digital.ah.avanza.sgc.module.user.entity.UserEntity;
import digital.ah.avanza.sgc.module.user.service.UserService;
import digital.ah.avanza.sgc.utils.exception.MessageException;
import digital.ah.avanza.sgc.utils.model.PageDTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmployeeService implements EmployeeImplement {

    private final EmployeeRepository employeeRepository;

    private final CompanyService companyService;

    private final CatalogItemService catalogItemService;

    private final UserService userService;

    @Transactional
    @Override
    public String create(EmployeeCreateDTO employee) {
        CompanyOneDTO companyDTO = this.companyService.findOne(employee.getCompany());
        CompanyEntity companyEntity = CompanyEntity
                .builder().id(companyDTO.getId()).build();

        CatalogItemOneDTO catalogItemDTO = this.catalogItemService.findOne(employee.getDocumentType());
        CatalogItemEntity catalogItemEntity = CatalogItemEntity
                .builder().id(catalogItemDTO.getId()).build();

        UserEntity userEntity = null;

        if (employee.getUser() != null) {
            userEntity = this.userService.create(employee.getUser());
            if (userEntity.getId() == 0) {
                throw new MessageException("Error creating user");
            }
        }

        Boolean employeeExists = this.employeeRepository
                .findByCompany_IdAndIdentityDocumentNumber(employee.getCompany(), employee.getIdentityDocumentNumber())
                .isPresent();
        if (employeeExists) {
            throw new MessageException("Employee already exists");
        }

        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .company(companyEntity)
                .documentType(catalogItemEntity)
                .user(userEntity)
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .identityDocumentNumber(employee.getIdentityDocumentNumber())
                .address(employee.getAddress())
                .city(employee.getCity())
                .phone(employee.getPhone())
                .email(employee.getEmail())
                .build();
        employeeEntity = this.employeeRepository.save(employeeEntity);
        return employeeEntity.getId() != 0 ? "Successfully Created" : "Error Creating Employee";
    }

    @Transactional
    @Override
    public String update(EmployeeUpdateDTO employee) {
        Optional<EmployeeEntity> employeeOpt = this.employeeRepository.findById(employee.getId());
        if (employeeOpt.isEmpty()) {
            throw new MessageException("Employee not found");
        }
        EmployeeEntity employeeEntity = employeeOpt.get();

        CatalogItemOneDTO catalogItemDTO = this.catalogItemService.findOne(employee.getDocumentType());
        CatalogItemEntity catalogItemEntity = CatalogItemEntity
                .builder().id(catalogItemDTO.getId()).build();

        UserEntity userEntity = null;

        if (employee.getUser() != null) {
            userEntity = this.userService.update(employee.getUser());
        }

        employeeEntity.setDocumentType(catalogItemEntity);
        employeeEntity.setUser(userEntity);
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());
        employeeEntity.setIdentityDocumentNumber(employee.getIdentityDocumentNumber());
        employeeEntity.setAddress(employee.getAddress());
        employeeEntity.setCity(employee.getCity());
        employeeEntity.setPhone(employee.getPhone());
        employeeEntity.setEmail(employee.getEmail());

        employeeEntity = this.employeeRepository.save(employeeEntity);
        return employeeEntity.getId() != 0 ? "Successfully Updated" : "Error Updating Employee";
    }

    @Transactional(readOnly = true)
    @Override
    public EmployeeOneDTO findOne(Long id) {
        Optional<EmployeeEntity> employeeOpt = this.employeeRepository.findById(id);
        if (employeeOpt.isEmpty()) {
            throw new MessageException("Employee not found");
        }
        return new EmployeeOneDTO(employeeOpt.get());
    }

    @Transactional(readOnly = true)
    @Override
    public PageDTO<EmployeePaginationDTO> pagination(Pageable pageable, Long companyId) {
        Page<EmployeeEntity> employeePage = this.employeeRepository.findByCompanyId(pageable, companyId);
        List<EmployeePaginationDTO> dto = employeePage.getContent().stream()
                .map(employee -> new EmployeePaginationDTO(employee)).toList();
        return new PageDTO<>(dto, employeePage.getNumber(), employeePage.getSize(), employeePage.getTotalElements());

    }

}
