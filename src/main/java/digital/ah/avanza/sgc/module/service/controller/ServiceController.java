package digital.ah.avanza.sgc.module.service.controller;

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

import digital.ah.avanza.sgc.module.service.dto.ServiceCreateDTO;
import digital.ah.avanza.sgc.module.service.dto.ServiceOneDTO;
import digital.ah.avanza.sgc.module.service.dto.ServicePaginationDTO;
import digital.ah.avanza.sgc.module.service.dto.ServiceUpdateDTO;
import digital.ah.avanza.sgc.module.service.service.ServiceService;
import digital.ah.avanza.sgc.utils.exception.MessageException;
import digital.ah.avanza.sgc.utils.model.PageDTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "service")
public class ServiceController {

    private final ServiceService serviceService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ServiceCreateDTO service) {
        Map<String, Object> response = new HashMap<>();
        try {
            String message = this.serviceService.create(service);
            response.put("message", message);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (MessageException ex) {
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody ServiceUpdateDTO service) {
        Map<String, Object> response = new HashMap<>();
        try {
            String message = this.serviceService.update(service);
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
            ServiceOneDTO service = this.serviceService.findOne(id);
            return new ResponseEntity<>(service, HttpStatus.OK);
        } catch (MessageException ex) {
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/pagination/{companyId}")
    public ResponseEntity<?> findOne(Pageable pageable, @PathVariable Long companyId) {
        Map<String, Object> response = new HashMap<>();
        try {
            PageDTO<ServicePaginationDTO> pagination = this.serviceService.pagination(pageable, companyId, "");
            return new ResponseEntity<>(pagination, HttpStatus.OK);
        } catch (MessageException ex) {
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
