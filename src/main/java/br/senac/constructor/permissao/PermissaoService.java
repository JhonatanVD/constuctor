package br.senac.constructor.permissao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PermissaoService{

    private PermissaoRepository permissaoRepository;

    public Permissao criarPermissao(PermissaoRepresentation.CriarOuAtualizar criar){
        return this.permissaoRepository.save(Permissao.builder()
                //.nome(criar.getNom)
                .build());
    }
}
