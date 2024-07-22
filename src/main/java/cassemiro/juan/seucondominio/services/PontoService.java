package cassemiro.juan.seucondominio.services;


import cassemiro.juan.seucondominio.dtos.PontoDto;
import cassemiro.juan.seucondominio.dtos.PontoRegistrarDto;
import cassemiro.juan.seucondominio.models.Funcionario;
import cassemiro.juan.seucondominio.models.Ponto;
import cassemiro.juan.seucondominio.repositories.FuncionarioRepository;
import cassemiro.juan.seucondominio.repositories.PontoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PontoService {

    @Autowired
    private PontoRepository pontoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<PontoDto> listarTodosRegistros(){
        return pontoRepository.findAll().stream().map(PontoDto::new).toList();
    }

    public PontoDto listarRegistroPorId(Long id){
        Ponto ponto = pontoRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Não foi possível encontrar o registro!"));
        return new PontoDto(ponto);
    }

    public PontoDto registrarPonto(PontoRegistrarDto dto){
        Funcionario funcionario = funcionarioRepository.findById(dto.funcionarioId()).orElseThrow(()-> new NoSuchElementException("Este funcionário não existe!"));
        if(!pontoRepository.findAll().isEmpty()) {
            Ponto ultimoPonto = pontoRepository.findLatestPontoByFuncionarioId(dto.funcionarioId()).get();
            if (ultimoPonto.getHorarioSaida() == null) {
                throw new RuntimeException("Você ainda tem um ponto aberto!");
            }
        }
        return new PontoDto(pontoRepository.saveAndFlush(new Ponto(funcionario)));
    }


    public PontoDto fecharPonto(PontoRegistrarDto dto){
        Funcionario funcionario = funcionarioRepository.findById(dto.funcionarioId())
                .orElseThrow(()->new NoSuchElementException("Não existe este funcionário"));
        Ponto ponto = pontoRepository.findLatestPontoByFuncionarioId(funcionario.getId())
                .orElseThrow(()-> new NoSuchElementException("Este registro não existe!"));
        if(ponto.getHorarioSaida() != null){
            throw new RuntimeException("Você não possui ponto em aberto!");
        }

        ponto.setHorarioSaida(Instant.now());
        pontoRepository.saveAndFlush(ponto);

        return new PontoDto(pontoRepository.findLatestPontoByFuncionarioId(dto.funcionarioId()).get());
    }

    public List<PontoDto> listarRegistrosPorIdFuncionario(Long id){
        if(!funcionarioRepository.existsById(id)){
            throw new NoSuchElementException("Este funcionário não existe!");
        }
        return pontoRepository.findAllByFuncionarioId(id).stream().map(PontoDto::new).toList();
    }


}
