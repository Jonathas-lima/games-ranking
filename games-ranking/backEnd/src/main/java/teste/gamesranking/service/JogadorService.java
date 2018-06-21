package teste.gamesranking.service;

import org.springframework.http.ResponseEntity;
import teste.gamesranking.model.Jogador;

import java.util.List;

public interface JogadorService {

     List<Jogador> getAllJogadores();
     Jogador getJogadorById(long id);
     Jogador saveJogador(Jogador jogador);
     ResponseEntity<?> deleteJogador(Jogador jogador);
}
