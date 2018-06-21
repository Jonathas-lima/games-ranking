package teste.gamesranking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import teste.gamesranking.exceptions.ResourceNotFoundException;
import teste.gamesranking.model.Jogador;
import teste.gamesranking.repository.JogadorRepository;

import java.util.List;

@Service("jogadorService")
public class JogadorServiceImpl implements JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository;

    @Override
    public List<Jogador> getAllJogadores(){
        return jogadorRepository.findAll();
    }

    @Override
    public Jogador getJogadorById(long id){
        return jogadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jogador", "ID", id));
    }

    @Override
    public Jogador saveJogador(Jogador jogador){
        return jogadorRepository.save(jogador);
    }

    @Override
    public ResponseEntity<?> deleteJogador(Jogador jogador){

        jogadorRepository.delete(jogador);
        return ResponseEntity.ok().build();
    }
}
