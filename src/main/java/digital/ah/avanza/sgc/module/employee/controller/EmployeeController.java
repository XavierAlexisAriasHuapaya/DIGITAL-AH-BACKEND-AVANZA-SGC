package digital.ah.avanza.sgc.module.employee.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import digital.ah.avanza.sgc.module.employee.dto.EmployeeCreateDTO;
import digital.ah.avanza.sgc.module.employee.dto.EmployeeOneDTO;
import digital.ah.avanza.sgc.module.employee.dto.EmployeePaginationDTO;
import digital.ah.avanza.sgc.module.employee.dto.EmployeeUpdateDTO;
import digital.ah.avanza.sgc.module.employee.service.EmployeeService;
import digital.ah.avanza.sgc.utils.exception.MessageException;
import digital.ah.avanza.sgc.utils.model.PageDTO;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@RestController
@RequestMapping(path = "employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EmployeeCreateDTO employee) {
        Map<String, Object> response = new HashMap<>();
        try {
            String message = this.employeeService.create(employee);
            response.put("message", message);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (MessageException ex) {
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody EmployeeUpdateDTO employee) {
        Map<String, Object> response = new HashMap<>();
        try {
            String message = this.employeeService.update(employee);
            response.put("message", message);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (MessageException ex) {
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            EmployeeOneDTO employee = this.employeeService.findOne(id);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (MessageException ex) {
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/pagination/{companyId}")
    public ResponseEntity<?> findOne(Pageable pageable, @PathVariable Long companyId) {
        Map<String, Object> response = new HashMap<>();
        try {
            PageDTO<EmployeePaginationDTO> pagination = this.employeeService.pagination(pageable, companyId);
            return new ResponseEntity<>(pagination, HttpStatus.OK);
        } catch (MessageException ex) {
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
