import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class MyMorpionStepDefs {

    @Given("The grid contains {string} at {string}")
    public void theGridContainsAt(String arg0, String arg1) {
        System.out.println("Symbole : " + arg0);
        System.out.println("Emplacement : " + arg1);
    }

    @When("Player {string} plays")
    public void playerPlays(String arg0) {
        System.out.println("Joueur :" + arg0);
    }

    @Then("He take place at {string}")
    public void heTakePlaceAt(String arg0) {
        System.out.println("Case choisie" + arg0);
    }
}
