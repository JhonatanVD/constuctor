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
    public ResponseEntity<Per.Detalhes> criarPermissao(
            @RequestBody @Valid PermissaoRepresentation.CriarOuAtualizar criar){


        Permissao permissao = this.permissaoService.criarPermissao(criar);

        PermissaoRepresentation.Detalhes detalhes = PermissaoRepresentation.Detalhes.from(permissao);
        return ResponseEntity.ok(detalhes);
    }
}
