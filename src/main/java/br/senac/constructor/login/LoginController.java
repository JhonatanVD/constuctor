package br.senac.constructor.login;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/login")
@Slf4j
public class LoginController {
    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<LoginRepresentation.Response> login(@RequestBody @Valid LoginRepresentation.Login login){
        return ResponseEntity.ok(this.loginService.generateToken(login));
    }
}
