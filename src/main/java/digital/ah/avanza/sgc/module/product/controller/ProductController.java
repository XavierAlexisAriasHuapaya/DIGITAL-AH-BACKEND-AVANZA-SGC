package digital.ah.avanza.sgc.module.product.controller;

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

import digital.ah.avanza.sgc.module.product.dto.ProductCreateDTO;
import digital.ah.avanza.sgc.module.product.dto.ProductOneDTO;
import digital.ah.avanza.sgc.module.product.dto.ProductPaginationDTO;
import digital.ah.avanza.sgc.module.product.dto.ProductUpdateDTO;
import digital.ah.avanza.sgc.module.product.service.ProductService;
import digital.ah.avanza.sgc.utils.exception.MessageException;
import digital.ah.avanza.sgc.utils.model.PageDTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductCreateDTO product) {
        Map<String, Object> response = new HashMap<>();
        try {
            String message = this.productService.create(product);
            response.put("message", message);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (MessageException ex) {
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody ProductUpdateDTO product) {
        Map<String, Object> response = new HashMap<>();
        try {
            String message = this.productService.update(product);
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
            ProductOneDTO product = this.productService.findOne(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (MessageException ex) {
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/pagination/{companyId}")
    public ResponseEntity<?> findOne(Pageable pageable, @PathVariable Long companyId) {
        Map<String, Object> response = new HashMap<>();
        try {
            PageDTO<ProductPaginationDTO> pagination = this.productService.pagination(pageable, companyId);
            return new ResponseEntity<>(pagination, HttpStatus.OK);
        } catch (MessageException ex) {
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
