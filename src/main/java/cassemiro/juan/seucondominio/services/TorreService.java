package cassemiro.juan.seucondominio.services;


import cassemiro.juan.seucondominio.dtos.TorreCadastroDto;
import cassemiro.juan.seucondominio.dtos.TorreDto;
import cassemiro.juan.seucondominio.models.Torre;
import cassemiro.juan.seucondominio.models.Unidade;
import cassemiro.juan.seucondominio.repositories.TorreRepository;
import cassemiro.juan.seucondominio.repositories.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TorreService {

    @Autowired
    private TorreRepository torreRepository;

    @Autowired
    private UnidadeRepository unidadeRepository;

    public List<TorreDto> listarTodasTorres(){
        return torreRepository.findAll().stream().map(TorreDto::new).toList();
    }

    public TorreDto listarTorrePorId(Long id){
        Torre torre = torreRepository.findById(id).orElseThrow(()->new NoSuchElementException("Torre não encontrada!"));
        return new TorreDto(torre);
    }

    public TorreDto cadastrarTorre(TorreCadastroDto dto){

        Torre torreNova = torreRepository.save(new Torre(dto));
        List<Unidade> listaUnidades = new ArrayList<Unidade>();
        for(int i = 1;i <= torreNova.getAndares();i++){
            for(int j = 1; j <= torreNova.getQtdUnidades();j++){
                String strNum = i+"0"+j;
                int numero = Integer.parseInt(strNum);
                Unidade novaUnidade = new Unidade(numero,torreNova);
                listaUnidades.add(novaUnidade);
            }
        }
        unidadeRepository.saveAll(listaUnidades);
        return new TorreDto(torreNova);
    }

    public void excluirTorrePorId(Long id){
        torreRepository.deleteById(id);
    }

}
