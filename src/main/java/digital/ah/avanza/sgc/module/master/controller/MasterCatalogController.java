package digital.ah.avanza.sgc.module.master.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import digital.ah.avanza.sgc.module.master.dto.MasterCatalogAllDTO;
import digital.ah.avanza.sgc.module.master.services.MasterCatalogService;
import digital.ah.avanza.sgc.utils.exception.MessageException;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@RequestMapping(path = "master-catalog")
@RestController
public class MasterCatalogController {

    private final MasterCatalogService masterCatalogService;

    @GetMapping
    public ResponseEntity<?> getAllCatalogs() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<MasterCatalogAllDTO> dto = this.masterCatalogService.getAllCatalogs();
            if (dto.isEmpty()) {
                response.put("message", "Empty");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (MessageException ex) {
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
