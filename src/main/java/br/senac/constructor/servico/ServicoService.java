package br.senac.constructor.servico;

import br.senac.constructor.categoria.Categoria;
import br.senac.constructor.categoria.CategoriaService;
import br.senac.constructor.exception.NotFoundException;
import br.senac.constructor.prestador.Prestador;
import br.senac.constructor.prestador.PrestadorService;;
import br.senac.constructor.utils.StatusEnum;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ServicoService {
    private ServicoRepository servicoRepository;
    private PrestadorService prestadorService;
    private CategoriaService categoriaService;
    public Servico criarServico(ServicoRepresentation.CriarOuAtualizar criar){
        Prestador prestador = this.prestadorService.buscarUmPrestador(criar.getPrestador());
        Categoria categoria = this.categoriaService.buscarUmaCategoria(criar.getCategoria());

        return this.servicoRepository.save(Servico.builder()
                        .valorServico(criar.getValorServico())
                        .diasTrabalho(criar.getDiasTrabalho())
                        .endereco(criar.getEndereco())
                        .descricao(criar.getDescricao())
                        .categoria(categoria)
                        .prestador(prestador)
                        .status(StatusEnum.ATIVO)
                .build());
    }

    public Page<Servico> buscarTodos(Pageable pageable) {
        return this.servicoRepository.findAll(pageable);
    }
    public Page<Servico> buscarTodos(Predicate filtroURI, Pageable pageable){
        return this.servicoRepository.findAll(pageable);
    }

    public Servico atualizar(Long idServico, ServicoRepresentation.CriarOuAtualizar atualizar) {
        Servico servicoParaAtualizar = Servico.builder()
                .valorServico(atualizar.getValorServico())
                .status(atualizar.getStatus())
                .diasTrabalho(atualizar.getDiasTrabalho())
                .endereco(atualizar.getEndereco())
                .descricao(atualizar.getDescricao())
                .build();

        return this.servicoRepository.save(servicoParaAtualizar);
    }

    public Servico buscarUmServico(Long idServico) {
        Optional<Servico> servicoAtual = this.servicoRepository.findById(idServico);
        if (servicoAtual.isPresent()) {
            return servicoAtual.get();
        } else {
            throw new NotFoundException("Serviço não encontrado");
        }
    }

    public void excluir(Long id) {
        servicoRepository.deleteById(id);
    }
}


