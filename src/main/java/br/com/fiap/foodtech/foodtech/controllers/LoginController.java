package br.com.fiap.foodtech.foodtech.controllers;

import br.com.fiap.foodtech.foodtech.dto.LoginDTO;
import br.com.fiap.foodtech.foodtech.entities.Login;
import br.com.fiap.foodtech.foodtech.service.LoginService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("login")
public class LoginController {

    private static final Log log = LogFactory.getLog(LoginController.class);
    private final LoginService loginService;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("{id}")
    public ResponseEntity<LoginDTO> login(@PathVariable("id") Long id){
        logger.info("GET -> /login/"+id);
        Optional<Login> loginOptional = this.loginService.findLogin(id);

        if(loginOptional.isPresent()){
            var login = loginOptional.get();
            LoginDTO loginDTO = new LoginDTO(
                    login.getId(),
                    login.getLogin(),
                    login.getSenha()
            );
            return ResponseEntity.ok(loginDTO);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateLogin(@PathVariable("id") Long id, @RequestBody LoginDTO loginDTO){
        logger.info("PUT -> /login/"+id);
        var login = loginDTO.mapearLogin();
        this.loginService.updateLogin(id, login);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
