package br.senac.constructor.Prestador;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PrestadorService {

    private PrestadorRepository prestadorRepository;
}