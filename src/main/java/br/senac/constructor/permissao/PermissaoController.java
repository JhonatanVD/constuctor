package br.senac.constructor.permissao;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/permissao")
@CrossOrigin("*")
@AllArgsConstructor
public class PermissaoController{

    private PermissaoService permissaoService;

    @PostMapping("/")
    public ResponseEntity<PermissaoRepresentation.Dettalhes> criarPermissao(
            @RequestBody @Valid PermissaoRepresentation.CriarOuAtualizar criar){

        Permissao permissao = this.permissaoService.criarPermissao(criar);

        return null;

    }

}
