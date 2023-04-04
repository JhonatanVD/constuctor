package br.senac.constructor.prestadorServico;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/Prestador")
@CrossOrigin("*")
@AllArgsConstructor
public class PrestadorController {

    private PrestadorService prestadorService;
}
