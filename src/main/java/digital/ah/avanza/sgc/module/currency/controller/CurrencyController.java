package digital.ah.avanza.sgc.module.currency.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import digital.ah.avanza.sgc.module.currency.dto.CurrencyCreateDTO;
import digital.ah.avanza.sgc.module.currency.dto.CurrencyOneDTO;
import digital.ah.avanza.sgc.module.currency.dto.CurrencyPaginationDTO;
import digital.ah.avanza.sgc.module.currency.dto.CurrencyUpdateDTO;
import digital.ah.avanza.sgc.module.currency.service.CurrencyService;
import digital.ah.avanza.sgc.utils.exception.MessageException;
import digital.ah.avanza.sgc.utils.model.PageDTO;
import lombok.AllArgsConstructor;

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

@AllArgsConstructor
@RestController
@RequestMapping(path = "currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CurrencyCreateDTO currency) {
        Map<String, Object> response = new HashMap<>();
        try {
            String message = this.currencyService.create(currency);
            response.put("message", message);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (MessageException ex) {
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody CurrencyUpdateDTO currency) {
        Map<String, Object> response = new HashMap<>();
        try {
            String message = this.currencyService.update(currency);
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
            CurrencyOneDTO dto = this.currencyService.findOne(id);
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
            PageDTO<CurrencyPaginationDTO> dto = this.currencyService.pagination(pageable, companyId);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (MessageException ex) {
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
