package cassemiro.juan.seucondominio.services;


import cassemiro.juan.seucondominio.dtos.EncomendaCadastroDto;
import cassemiro.juan.seucondominio.dtos.EncomendaDto;
import cassemiro.juan.seucondominio.dtos.EncomendaEdicaoDto;
import cassemiro.juan.seucondominio.models.Encomenda;
import cassemiro.juan.seucondominio.models.Funcionario;
import cassemiro.juan.seucondominio.models.Morador;
import cassemiro.juan.seucondominio.repositories.EncomendaRepository;
import cassemiro.juan.seucondominio.repositories.FuncionarioRepository;
import cassemiro.juan.seucondominio.repositories.MoradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EncomendaService {

    @Autowired
    private EncomendaRepository encomendaRepository;

    @Autowired
    private MoradorRepository moradorRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<EncomendaDto> listarTodasEncomendas(int page){
        Pageable pageable = PageRequest.of(page,10);
        return encomendaRepository.findAll(pageable).stream().map(EncomendaDto::new).toList();
    }

    public EncomendaDto listarEncomendaPorId(Long id){
         return new EncomendaDto(encomendaRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Esta encomenda não existe!")));
    }

    public List<EncomendaDto> listarEncomendasPorMorador(Long idMorador, int page){
        Pageable pageable = PageRequest.of(page,10);
        return encomendaRepository.findAlLByMoradorId(idMorador,pageable).stream().map(EncomendaDto::new).toList();
    }
    public List<EncomendaDto> listarEncomendasPorFuncionario(Long idFuncionario, int page){
        Pageable pageable = PageRequest.of(page,10);
        return encomendaRepository.findAllByFuncionarioId(idFuncionario,pageable).stream().map(EncomendaDto::new).toList();
    }
    public List<EncomendaDto> listarEncomendasPorUnidadeId(Long idUnidade, int page){
        Pageable pageable = PageRequest.of(page,10);
        return encomendaRepository.findAllByUnidadeId(idUnidade,pageable).stream().map(EncomendaDto::new).toList();
    }

    public EncomendaDto editarEncomenda(EncomendaEdicaoDto dto){
            Encomenda encomenda = encomendaRepository.findById(dto.id()).orElseThrow(()->new NoSuchElementException("Essa encomenda não existe!"));
            Morador morador = moradorRepository.findById(dto.moradorId()).orElseThrow(()-> new NoSuchElementException("Este morador não existe!"));
            Funcionario funcionario = funcionarioRepository.findById(dto.funcionarioId()).orElseThrow(()-> new NoSuchElementException("Este funcionário não existe!"));

            encomenda.setDescricao(dto.descricao());
            encomenda.setPeso(dto.peso());
            encomenda.setAltura(dto.altura());
            encomenda.setComprimento(dto.comprimento());
            encomenda.setLargura(dto.largura());
            encomenda.setDataEntrega(dto.dataEntrega());
            encomenda.setEntregue(dto.entregue());
            encomenda.setMorador(morador);
            encomenda.setFuncionario(funcionario);
            encomendaRepository.save(encomenda);
            return new EncomendaDto(encomenda);
    }

    public EncomendaDto cadastrarEncomenda(EncomendaCadastroDto encomenda){
            Morador morador = moradorRepository.findById(encomenda.moradorId()).orElseThrow(() -> new NoSuchElementException("Este morador não existe!"));
            Funcionario funcionario = funcionarioRepository.findById(encomenda.funcionarioId()).orElseThrow(() -> new NoSuchElementException("Este funcionário não existe!"));
            Encomenda encomendaNova = new Encomenda(encomenda, morador, funcionario);
            encomendaRepository.save(encomendaNova);
            return new EncomendaDto(encomendaNova);
    }

    public void deletarEncomendaPorId(Long id){
        encomendaRepository.deleteById(id);
    }

}
