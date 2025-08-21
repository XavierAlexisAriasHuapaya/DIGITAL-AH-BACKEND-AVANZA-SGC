package digital.ah.avanza.sgc.module.rol.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import digital.ah.avanza.sgc.module.rol.dto.RolAllDTO;
import digital.ah.avanza.sgc.module.rol.dto.RolOneDTO;
import digital.ah.avanza.sgc.module.rol.service.RolService;
import digital.ah.avanza.sgc.utils.exception.MessageException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "rol")
public class RolController {

    private final RolService rolService;

    @GetMapping(path = "/company/{companyId}")
    public ResponseEntity<?> getAll(@PathVariable Long companyId) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<RolAllDTO> dto = this.rolService.getAll(companyId);
            if (dto.size() == 0) {
                response.put("message", "No records found");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (MessageException ex) {
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            RolOneDTO dto = this.rolService.findOne(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (MessageException ex) {
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

}
