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
import teste.gamesranking.service.JogadorService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    //retorna todos os jogadores
    @GetMapping("/jogadores")
    public List<Jogador> listarTodosJogadores(){

        return jogadorService.getAllJogadores();

    }

    @PostMapping("/jogador")
    public Jogador criarJogador(@Valid @RequestBody Jogador jogador) {

        try{
            jogador.validate();
            return jogadorService.saveJogador(jogador);

        }catch(NameInvalidException e){
            // troca o caractere especial por um espaco
            System.out.println("[POST]: "+e.getMessage());
            jogador.setNome(jogador.getNome().replaceAll("[-!@#$%&*()=+.^:;?{}<>,]"," "));

            return jogadorService.saveJogador(jogador);

        }catch (InconsistentDataException e){
            // se o numero de vitorias > numero partidas, igualar o numero de vitorias ao de partidas
            System.out.println("[POST]: "+e.getMessage());
            jogador.setNumeroVitorias(jogador.getNumeroPartidas());

            return jogadorService.saveJogador(jogador);

        }catch (NegativeNumberException e){
            // se receber dados negativos troca por 0
            System.out.println("[POST]: "+e.getMessage());
            if(e.getField().equals("partidas")){
                jogador.setNumeroPartidas(0);// 0 devido a ser o numero negativo
                jogador.setNumeroVitorias(0);// nao pode ser maior que numero partidas
            }else{
                jogador.setNumeroVitorias(0);// 0 devido a ser o numero negativo
            }
            return jogadorService.saveJogador(jogador);
        }
    }

    @PutMapping("/jogador/{id}")
    public Jogador atualizarJogador(@PathVariable(value = "id") Long jogadorId,
                                    @Valid @RequestBody Jogador jogadorDetalhes){

        Jogador jogador = new Jogador();
        try{
            //busca um jogador pelo id recebido
            jogador = jogadorService.getJogadorById(jogadorId);

            //atualiza os dados
            jogador.setNome(jogadorDetalhes.getNome());
            jogador.setNumeroPartidas(jogadorDetalhes.getNumeroPartidas());
            jogador.setNumeroVitorias(jogadorDetalhes.getNumeroVitorias());

            // validacao dos dados recebidos
            jogador.validate();
            return jogadorService.saveJogador(jogador);//salva no bd

            //os blocos de catch se comportam igualmente ao POST
        }catch(NameInvalidException e){
            System.out.println("[PUT]: "+e.getMessage());
            jogador.setNome(jogador.getNome().replaceAll("[-!@#$%&*()=+.^:;?{}<>,]"," "));

            return jogadorService.saveJogador(jogador);

        }catch (InconsistentDataException e){
            System.out.println("[PUT]: "+e.getMessage());
            jogador.setNumeroVitorias(jogador.getNumeroPartidas());

            return jogadorService.saveJogador(jogador);

        }catch (NegativeNumberException e){
            System.out.println("[PUT]: "+e.getMessage());
            if(e.getField().equals("partidas")){
                jogador.setNumeroPartidas(0);// 0 devido a ser o numero negativo
                jogador.setNumeroVitorias(0);// nao pode ser maior que numero partidas
            }else{
                jogador.setNumeroVitorias(0);// 0 devido a ser o numero negativo
            }
            return jogadorService.saveJogador(jogador);
        }
    }

    @DeleteMapping("/jogador/{id}")
    public ResponseEntity <?> excluirJogador(@PathVariable(value = "id") Long jogadorId){

        //busca o jogador pelo o id
        Jogador jogador = jogadorService.getJogadorById(jogadorId);

        jogadorService.deleteJogador(jogador);
        return ResponseEntity.ok().build();
    }
}
