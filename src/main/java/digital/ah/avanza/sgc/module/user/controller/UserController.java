package digital.ah.avanza.sgc.module.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import digital.ah.avanza.sgc.module.user.dto.UserOneDTO;
import digital.ah.avanza.sgc.module.user.service.UserService;
import digital.ah.avanza.sgc.utils.exception.MessageException;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
@RestController
@RequestMapping(path = "user")
public class UserController {

    private final UserService userService;

    @ExceptionHandler(MessageException.class)
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            UserOneDTO dto = this.userService.findOne(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (MessageException ex) {
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
