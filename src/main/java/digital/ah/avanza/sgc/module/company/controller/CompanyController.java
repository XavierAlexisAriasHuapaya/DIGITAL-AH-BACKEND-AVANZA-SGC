package digital.ah.avanza.sgc.module.company.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import digital.ah.avanza.sgc.module.company.dto.CompanyCreateDTO;
import digital.ah.avanza.sgc.module.company.dto.CompanyOneDTO;
import digital.ah.avanza.sgc.module.company.dto.CompanyPaginationDTO;
import digital.ah.avanza.sgc.module.company.dto.CompanyUpdateDTO;
import digital.ah.avanza.sgc.module.company.service.CompanyService;
import digital.ah.avanza.sgc.utils.exception.MessageException;
import digital.ah.avanza.sgc.utils.model.PageDTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "company")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CompanyCreateDTO company) {
        Map<String, Object> response = new HashMap<>();
        try {
            String message = this.companyService.create(company);
            response.put("message", message);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (MessageException ex) {
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody CompanyUpdateDTO company) {
        Map<String, Object> response = new HashMap<>();
        try {
            String message = this.companyService.update(company);
            response.put("message", message);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (MessageException ex) {
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            CompanyOneDTO company = this.companyService.findOne(id);
            return new ResponseEntity<>(company, HttpStatus.OK);
        } catch (MessageException ex) {
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/pagination")
    public ResponseEntity<?> pagination(Pageable pageable) {
        Map<String, Object> response = new HashMap<>();
        try {
            PageDTO<CompanyPaginationDTO> pagination = this.companyService.pagination(pageable, "");
            return new ResponseEntity<>(pagination, HttpStatus.OK);
        } catch (MessageException ex) {
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
