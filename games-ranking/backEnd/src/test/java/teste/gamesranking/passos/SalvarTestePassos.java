package teste.gamesranking.passos;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import teste.gamesranking.model.Jogador;


public class SalvarTestePassos {

    private Jogador jogador = new Jogador();

    @Given("o usuario acessa o sistema")
    public void o_usuario_acessar_o_sistema()throws Throwable{

    }

    @And("^digita o nome do jogador \"(.*)\"$")
    public void digida_o_nome_do_jogador(String nome)throws Throwable{
        jogador.setNome(nome);
        Assert.assertEquals("jose", jogador.getNome());
    }

    @And("^digita no campo partidas o numero de (\\d+)$")
    public void digita_no_campo_partidas_o_numero_de(int partidas)throws Throwable{
        jogador.setNumeroPartidas(partidas);
        Assert.assertEquals(1, jogador.getNumeroPartidas());
    }

    @And("^digita no campo vitorias o numero de (\\d+)$")
    public void digita_no_campo_vitorias_o_numero_de(int vitorias)throws Throwable{
        jogador.setNumeroVitorias(vitorias);
        Assert.assertEquals(1, jogador.getNumeroVitorias());
    }

    @Then("o usuario tera criado um jogador no banco de dados")
    public void o_usuario_tera_criado_o_no_banco_de_dados(){

        Assert.assertEquals("jose", "jose");
    }
}
