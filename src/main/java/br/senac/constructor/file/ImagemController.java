package br.senac.constructor.file;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/files")
@Slf4j
@AllArgsConstructor
public class ImagemController {
    private final ImagemService service;

    @PostMapping
    public void uploadFile(@RequestParam("files") List<MultipartFile> file,
                           @RequestHeader("id-servico") Long idServico) {
        this.service.save(file, idServico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> download(@PathVariable Long id) {
        Imagem imagem = service.buscarImagem(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(imagem.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imagem.getFileName() + "\"")
                .body(new ByteArrayResource(imagem.getFile()));
    }
}