package teste.gamesranking;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import teste.gamesranking.model.Jogador;
import teste.gamesranking.repository.JogadorRepository;
import teste.gamesranking.service.JogadorServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
public class JogadorServiceTest {

    @Mock
    private JogadorRepository jogadorRepository;

    @InjectMocks
    private JogadorServiceImpl jogadorService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllJogadores(){
        List<Jogador> jogadorList = new ArrayList<>();

        Jogador jogador1 = new Jogador();

        jogador1.setNome("Teste1");
        jogador1.setNumeroVitorias(2);
        jogador1.setNumeroPartidas(2);

        jogadorList.add(jogador1);

        Mockito.when(jogadorRepository.findAll()).thenReturn(jogadorList);

        List<Jogador> result = jogadorService.getAllJogadores();
        Assert.assertEquals(1,result.size());
    }

    @Test
    public void testFindById(){

        Jogador jogador = new Jogador();
        jogador.setNome("teste");
        jogador.setNumeroVitorias(0);
        jogador.setNumeroPartidas(0);

        //o metodo findById retorna um optional
        Optional<Jogador> optional = Optional.of(jogador);

        Mockito.when(jogadorRepository.findById(1L)).thenReturn(optional);

        Jogador result = jogadorService.getJogadorById(1L);
        Assert.assertEquals("teste",result.getNome());
        Assert.assertEquals(0,result.getNumeroPartidas());
        Assert.assertEquals(0,result.getNumeroVitorias());
    }

    @Test
    public void testSaveJogador(){

        Jogador jogador = new Jogador();
        jogador.setNome("teste");
        jogador.setNumeroVitorias(0);
        jogador.setNumeroPartidas(0);

        Mockito.when(jogadorRepository.save(jogador)).thenReturn(jogador);

        Jogador result = jogadorService.saveJogador(jogador);
        Assert.assertEquals("teste",result.getNome());
        Assert.assertEquals(0,result.getNumeroPartidas());
        Assert.assertEquals(0,result.getNumeroVitorias());
    }

    @Test
    public void testDelete(){
        Jogador jogador = new Jogador();
        jogador.setNome("teste");
        jogador.setNumeroVitorias(0);
        jogador.setNumeroPartidas(0);

        jogadorService.deleteJogador(jogador);
        Mockito.verify(jogadorRepository,Mockito.times(1)).delete(jogador);
    }
}
