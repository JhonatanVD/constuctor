package br.senac.constructor.permissao;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class PermissaoService{

    private PermissaoRepository permissaoRepository;

    public Permissao criarPermissao(PermissaoRepresentation.CriarOuAtualizar criar){
        return this.permissaoRepository.save(Permissao.builder()
                .nome(criar.getNome())
                .build());
    }
}
