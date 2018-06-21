package teste.gamesranking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teste.gamesranking.exceptions.InconsistentDataException;
import teste.gamesranking.exceptions.NameInvalidException;
import teste.gamesranking.exceptions.NegativeNumberException;
import teste.gamesranking.exceptions.ResourceNotFoundException;
import teste.gamesranking.model.Jogador;
import teste.gamesranking.repository.JogadorRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class JogadorController {

    @Autowired
    JogadorRepository jogadorRepository; //instancia do repositorio de dados

    //retorna todos os jogadores
    @GetMapping("/jogadores")
    public List<Jogador> listarTodosJogadores(){

        return jogadorRepository.findAll();

    }

    @PostMapping("/jogador")
    public Jogador criarJogador(@Valid @RequestBody Jogador jogador) {

        try{
            jogador.validate();
            return jogadorRepository.save(jogador);

        }catch(NameInvalidException e){
            // troca o caractere especial por um espaco
            System.out.println("[POST]: "+e.getMessage());
            jogador.setNome(jogador.getNome().replaceAll("[-!@#$%&*()=+.^:;?{}<>,]"," "));

            return jogadorRepository.save(jogador);

        }catch (InconsistentDataException e){
            // se o numero de vitorias > numero partidas, igualar o numero de vitorias ao de partidas
            System.out.println("[POST]: "+e.getMessage());
            jogador.setNumeroVitorias(jogador.getNumeroPartidas());

            return jogadorRepository.save(jogador);

        }catch (NegativeNumberException e){
            // se receber dados negativos troca por 0
            System.out.println("[POST]: "+e.getMessage());
            if(e.getField().equals("partidas")){
                jogador.setNumeroPartidas(0);// 0 devido a ser o numero negativo
                jogador.setNumeroVitorias(0);// nao pode ser maior que numero partidas
            }else{
                jogador.setNumeroVitorias(0);// 0 devido a ser o numero negativo
            }
            return jogadorRepository.save(jogador);
        }
    }

    @PutMapping("/jogador/{id}")
    public Jogador atualizarJogador(@PathVariable(value = "id") Long jogadorId,
                                    @Valid @RequestBody Jogador jogadorDetalhes){

        Jogador jogador = new Jogador();
        try{
            //busca um jogador pelo id recebido
            jogador = jogadorRepository.findById(jogadorId)
                    .orElseThrow(() -> new ResourceNotFoundException("Jogador", "ID", jogadorId));

            //atualiza os dados
            jogador.setNome(jogadorDetalhes.getNome());
            jogador.setNumeroPartidas(jogadorDetalhes.getNumeroPartidas());
            jogador.setNumeroVitorias(jogadorDetalhes.getNumeroVitorias());

            // validacao dos dados recebidos
            jogador.validate();
            return jogadorRepository.save(jogador);//salva no bd

            //os blocos de catch se comportam igualmente ao POST
        }catch(NameInvalidException e){
            System.out.println("[PUT]: "+e.getMessage());
            jogador.setNome(jogador.getNome().replaceAll("[-!@#$%&*()=+.^:;?{}<>,]"," "));

            return jogadorRepository.save(jogador);

        }catch (InconsistentDataException e){
            System.out.println("[PUT]: "+e.getMessage());
            jogador.setNumeroVitorias(jogador.getNumeroPartidas());

            return jogadorRepository.save(jogador);

        }catch (NegativeNumberException e){
            System.out.println("[PUT]: "+e.getMessage());
            if(e.getField().equals("partidas")){
                jogador.setNumeroPartidas(0);// 0 devido a ser o numero negativo
                jogador.setNumeroVitorias(0);// nao pode ser maior que numero partidas
            }else{
                jogador.setNumeroVitorias(0);// 0 devido a ser o numero negativo
            }
            return jogadorRepository.save(jogador);
        }
    }

    @DeleteMapping("/jogador/{id}")
    public ResponseEntity <?> excluirJogador(@PathVariable(value = "id") Long jogadorId){

        //busca o jogador pelo o id
        Jogador jogador = jogadorRepository.findById(jogadorId)
                .orElseThrow(() -> new ResourceNotFoundException("Jogador", "ID", jogadorId));

        jogadorRepository.delete(jogador);
        return ResponseEntity.ok().build();
    }
}
