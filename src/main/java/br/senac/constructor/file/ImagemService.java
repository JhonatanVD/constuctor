package br.senac.constructor.file;

import br.senac.constructor.exception.NotFoundException;
import br.senac.constructor.exception.UploadFileException;
import br.senac.constructor.servico.Servico;
import br.senac.constructor.servico.ServicoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class ImagemService {
    private final ImagemRepository repository;
    private final ServicoService servicoService;
    public void save(List<MultipartFile> files, Long idServico) {
        log.info("Salvando imagem no banco...");

        Servico servico = this.servicoService.buscarUmServico(idServico);

        files.forEach(file -> {
            log.info("Nome: {}", file.getOriginalFilename());
            try {
                Imagem newImagem = Imagem.builder()
                            .file(file.getBytes())
                            .fileName(Objects.requireNonNull(file.getOriginalFilename()))
                            .fileType(file.getContentType())
                            .servico(servico)
                            .build();

                    repository.save(newImagem);
                } catch (Exception ex) {
                    log.error(ex.getMessage());
                    throw new UploadFileException("Falha ao salvar imagem");
                }
            });
    }

    public Imagem buscarImagem(Long id) {
        return this.repository.findById(id).orElseThrow(() -> new NotFoundException("Imagem nao encontrada com o id %s".formatted(id)));
    }
}