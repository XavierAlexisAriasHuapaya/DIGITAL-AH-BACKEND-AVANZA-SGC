package digital.ah.avanza.sgc.module.product.controller.brand;

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

import digital.ah.avanza.sgc.module.product.dto.brand.BrandCreateDTO;
import digital.ah.avanza.sgc.module.product.dto.brand.BrandOneDTO;
import digital.ah.avanza.sgc.module.product.dto.brand.BrandPaginationDTO;
import digital.ah.avanza.sgc.module.product.dto.brand.BrandUpdateDTO;
import digital.ah.avanza.sgc.module.product.service.brand.BrandService;
import digital.ah.avanza.sgc.utils.exception.MessageException;
import digital.ah.avanza.sgc.utils.model.PageDTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "brand")
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BrandCreateDTO brand) {
        Map<String, Object> response = new HashMap<>();
        try {
            String message = this.brandService.create(brand);
            response.put("message", message);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (MessageException ex) {
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody BrandUpdateDTO brand) {
        Map<String, Object> response = new HashMap<>();
        try {
            String message = this.brandService.update(brand);
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
            BrandOneDTO dto = this.brandService.findOne(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (MessageException ex) {
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/pagination/{companyId}")
    public ResponseEntity<?> pagination(Pageable pageable, @PathVariable Long companyId) {
        Map<String, Object> response = new HashMap<>();
        try {
            PageDTO<BrandPaginationDTO> dto = this.brandService.pagination(pageable, companyId);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (MessageException ex) {
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
