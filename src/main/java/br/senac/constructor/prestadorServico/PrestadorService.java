package br.senac.constructor.prestadorServico;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PrestadorService {

    private PrestadorRepository prestadorRepository;
}