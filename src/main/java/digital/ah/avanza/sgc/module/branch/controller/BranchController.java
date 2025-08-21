package digital.ah.avanza.sgc.module.branch.controller;

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

import digital.ah.avanza.sgc.module.branch.dto.BranchCreateDTO;
import digital.ah.avanza.sgc.module.branch.dto.BranchOneDTO;
import digital.ah.avanza.sgc.module.branch.dto.BranchPaginationDTO;
import digital.ah.avanza.sgc.module.branch.dto.BranchUpdateDTO;
import digital.ah.avanza.sgc.module.branch.service.BranchService;
import digital.ah.avanza.sgc.utils.exception.MessageException;
import digital.ah.avanza.sgc.utils.model.PageDTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "branch")
public class BranchController {

    private final BranchService branchService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BranchCreateDTO branch) {
        Map<String, Object> response = new HashMap<>();
        try {
            String message = this.branchService.create(branch);
            response.put("message", message);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (MessageException ex) {
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody BranchUpdateDTO branch) {
        Map<String, Object> response = new HashMap<>();
        try {
            String message = this.branchService.update(branch);
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
            BranchOneDTO branch = this.branchService.findOne(id);
            return new ResponseEntity<>(branch, HttpStatus.OK);
        } catch (MessageException ex) {
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/pagination/{companyId}")
    public ResponseEntity<?> findOne(Pageable pageable, @PathVariable Long companyId) {
        Map<String, Object> response = new HashMap<>();
        try {
            PageDTO<BranchPaginationDTO> pagination = this.branchService.pagination(pageable, companyId, "");
            return new ResponseEntity<>(pagination, HttpStatus.OK);
        } catch (MessageException ex) {
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
