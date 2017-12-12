
import user_interface.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import data_access.Database;
import data_access.DbBlogiDAO;
import data_access.DbKirjaDAO;
import data_access.DbKommenttiDAO;
import data_access.DbPodcastDAO;
import data_access.DbVideoDAO;
import data_access.MasterDAO;
/*
import java.io.File;
import java.io.FileWriter;
 */
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.junit.Assert.assertTrue;

public class Stepdefs {

    TextUI ui;
    StubIO io;
    String[] inputLines = new String[100];

    Database database;
    DbKirjaDAO kirjaDAO;
    DbKommenttiDAO kommenttiDAO;
    DbBlogiDAO blogiDAO;
    MasterDAO dao;
    DbVideoDAO videoDAO;
    DbPodcastDAO podcastDAO;

    String randomTitle;

    @Given("^system is asking input from user$")
    public void system_is_asking_input_from_user() throws Throwable {

        database = new Database("lukuvinkkikirjasto.db");
        kirjaDAO = new DbKirjaDAO(database);
        kommenttiDAO = new DbKommenttiDAO(database);
        blogiDAO = new DbBlogiDAO(database);
        videoDAO = new DbVideoDAO(database);
        podcastDAO = new DbPodcastDAO(database);
        dao = new MasterDAO(kirjaDAO, blogiDAO, kommenttiDAO, videoDAO, podcastDAO);
        inputLines[0] = "2"; // Tällä siirrytään listaukseen
    }

    @When("^empty string is entered$")
    public void empty_string_is_entered() throws Throwable {
        inputLines[1] = ""; // Sitten palataan
        inputLines[2] = ""; // ja poistutaan ohjelmasta
        io = new StubIO(inputLines);
        ui = new TextUI(io, dao);
        ui.run();
    }

    @Then("^system will navigate back$")
    public void system_will_navigate_back() throws Throwable {
        ArrayList<String> outputs = io.getOutputs();
        int lastIndex = outputs.size() - 1;
        assertTrue(outputs.get(lastIndex - 1).contains("Komento (1=lisää, 2=listaa kaikki, 3=listaa lukemattomat, 4=listaa luetut, \"\"=lopeta):"));
        assertTrue(outputs.get(lastIndex).contains("Kiitos ja näkemiin!"));
    }

    // marked as read feature
    @Given("^listed book details and chosed book to mark as read$")
    public void input_for_adding_book_and_marking_it_as_read() throws Throwable {
        database = new Database("lukuvinkkikirjasto.db");
        kirjaDAO = new DbKirjaDAO(database);
        kommenttiDAO = new DbKommenttiDAO(database);
        blogiDAO = new DbBlogiDAO(database);
        videoDAO = new DbVideoDAO(database);
        dao = new MasterDAO(kirjaDAO, blogiDAO, kommenttiDAO, videoDAO, podcastDAO);
        inputLines = new String[100];
        inputLines[0] = "2";
        inputLines[1] = "1";
    }

    @When("^marked as read$")
    public void marked_as_read() throws Throwable {
        inputLines[2] = "m";
        inputLines[3] = "";
        inputLines[4] = "";
        io = new StubIO(inputLines);
        ui = new TextUI(io, dao);
        ui.run();
    }

    @Then("^user can see it is marked as read$")
    public void user_can_see_it_is_marked_as_read() throws Throwable {
        ArrayList<String> outputs = io.getOutputs();
        int lastIndex = outputs.size() - 1;
        /*
        File file = new File("outputs.txt");
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        for (int i = 0; i <= lastIndex; i++) {
            writer.write(i+":"+outputs.get(i)+"\n");
        }
        writer.flush();
        writer.close();
         */
        assertTrue(outputs.get(lastIndex - 8).
                contains("Jackson, Shirley     | Onhan noita linnassa | 666                  | kyllä"));
    }

    @Given("^a Lukuvinkki is selected$")
    public void a_Lukuvinkki_is_selected() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^a comment is given with first line \"([^\"]*)\" and second line \"([^\"]*)\"$")
    public void a_comment_is_given_with_first_line_and_second_line(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the comment is shown on on first line \"([^\"]*)\" and second line \"([^\"]*)\"$")
    public void the_comment_is_shown_on_on_first_line_and_second_line(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^create new lukuvinkki is selected$")
    public void create_new_lukuvinkki_is_selected() throws Throwable {
        inputLines[0] = "1";
    }

    @Then("^podcast is available in the menu$")
    public void podcast_is_available_in_the_menu() throws Throwable {
        inputLines[1] = "";
        inputLines[2] = "";
        io = new StubIO(inputLines);
        ui = new TextUI(io, dao);
        ui.run();
        assertTrue(io.getOutputs().contains("Komento (1=kirja, 2=blogi, 3=video, 4=podcast, \"\"=palaa):"));
    }

    @Given("^creating a new podcast is selected$")
    public void creating_a_new_podcast_is_selected() throws Throwable {
        inputLines[1] = "4";
    }

    @Given("^author \"([^\"]*)\" a random title and URL \"([^\"]*)\" are entered$")
    public void author_a_random_title_and_URL_are_entered(String author, String url) throws Throwable {
        randomTitle = UUID.randomUUID().toString();
        inputLines[2] = randomTitle;
        inputLines[3] = author;
        inputLines[4] = url;
    }

    @When("^all items are listed$")
    public void all_items_are_listed() throws Throwable {
        inputLines[5] = "2";
    }

    @Then("^the random title appears on the list$")
    public void the_random_title_appears_on_the_list() throws Throwable {
        inputLines[6] = "";
        inputLines[7] = "";
        io = new StubIO(inputLines);
        ui = new TextUI(io, dao);
        ui.run();
        assertTrue(outputsContains(io, randomTitle.substring(0, 19)));
        
        
    }
    
    boolean outputsContains(StubIO io, String string) {
       for (String line : io.getOutputs()) {
           if (line.contains(string)) {
               return true;
           }           
       }
       return false;
    }

}
