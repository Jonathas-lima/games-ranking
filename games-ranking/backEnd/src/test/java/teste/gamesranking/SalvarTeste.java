package teste.gamesranking;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources/caracteristicas",tags = "@SalvarTeste",
glue = "teste.gamesranking.passos",monochrome = true, dryRun = false)
public class SalvarTeste {
}
