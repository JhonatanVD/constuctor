package br.senac.constructor.Prestador;

import br.senac.constructor.permissao.Permissao;
import br.senac.constructor.permissao.PermissaoRepresentation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/Prestador")
@CrossOrigin("*")
@AllArgsConstructor
public class PrestadorController {

    private PrestadorService prestadorService;

    @PostMapping
    public ResponseEntity<PrestadorRepresentation.Detalhes> criarPrestador(
            @RequestBody @Valid PrestadorRepresentation.CriarOuAtualizar criar){


        Prestador prestador = this.prestadorService.criarPrestador(criar);

        PrestadorRepresentation.Detalhes detalhes = PrestadorRepresentation.Detalhes.from(prestador);
        return ResponseEntity.ok(detalhes);
    }
}
