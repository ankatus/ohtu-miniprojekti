
import domain.Kirja;
import org.junit.Before;
import tools.TextTools;
import user_interface.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import data_access.*;

/*
import java.io.File;
import java.io.FileWriter;
 */
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Stepdefs {

    TextUI ui;
    StubIO io;
    ArrayList<String> inputLines;

    Database database;
    DbKirjaDAO kirjaDAO;
    DbKommenttiDAO kommenttiDAO;
    DbBlogiDAO blogiDAO;
    MasterDAO dao;
    DbVideoDAO videoDAO;
    DbPodcastDAO podcastDAO;
    String randomTitle;
    TagDAO tagDAO;
    
    @cucumber.api.java.Before
    public void setUp() throws ClassNotFoundException {
        inputLines = new ArrayList<>();
        database = new Database("testitietokanta.db");
        kirjaDAO = new DbKirjaDAO(database);
        kommenttiDAO = new DbKommenttiDAO(database);
        blogiDAO = new DbBlogiDAO(database);
        videoDAO = new DbVideoDAO(database);
        podcastDAO = new DbPodcastDAO(database);
        tagDAO = new DbTagDAO(database);
        dao = new MasterDAO(kirjaDAO, blogiDAO, kommenttiDAO, videoDAO, podcastDAO, tagDAO);       
    }

    @Given("^system is asking input from user$")
    public void system_is_asking_input_from_user() throws Throwable {

        database = new Database("testitietokanta.db");
        kirjaDAO = new DbKirjaDAO(database);
        kommenttiDAO = new DbKommenttiDAO(database);
        blogiDAO = new DbBlogiDAO(database);
        videoDAO = new DbVideoDAO(database);
        podcastDAO = new DbPodcastDAO(database);
        dao = new MasterDAO(kirjaDAO, blogiDAO, kommenttiDAO, videoDAO, podcastDAO, tagDAO);
        inputLines.add("2"); // Tällä siirrytään listaukseen
    }

    @When("^empty string is entered$")
    public void empty_string_is_entered() throws Throwable {
        runTestUI();
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
    }

    @When("^marked as read$")
    public void marked_as_read() throws Throwable {
        inputLines.add("m");
        runTestUI();
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

//    @Given("^system is asking input from user$")
//    public void system_is_asking_input_from_user() throws Throwable {
//
//        database = new Database("lukuvinkkikirjasto.db");
//        kirjaDAO = new DbKirjaDAO(database);
//        kommenttiDAO = new DbKommenttiDAO(database);
//        blogiDAO = new DbBlogiDAO(database);
//        videoDAO = new DbVideoDAO(database);
//        dao = new MasterDAO(kirjaDAO, blogiDAO, kommenttiDAO, videoDAO);
//        inputLines[0] = "2"; // Tällä siirrytään listaukseen
//    }
//
//    @When("^empty string is entered$")
//    public void empty_string_is_entered() throws Throwable {
//        inputLines[1] = ""; // Sitten palataan
//        inputLines[2] = ""; // ja poistutaan ohjelmasta
//        io = new StubIO(inputLines);
//        ui = new TextUI(io, dao);
//	ui.run();
//    }
//
//    @Then("^system will navigate back$")
//    public void system_will_navigate_back() throws Throwable {
//        ArrayList<String> outputs = io.getOutputs();
//        int lastIndex = outputs.size() - 1;
//        assertTrue(outputs.get(lastIndex - 1).contains("Komento (1=lisää, 2=listaa kaikki, 3=listaa lukemattomat, 4=listaa luetut, \"\"=lopeta):"));
//        assertTrue(outputs.get(lastIndex).contains("Kiitos ja näkemiin!"));
//    }
//
//    // marked as read feature
//
//    @Given("^listed book details and chosed book to mark as read$")
//    public void input_for_adding_book_and_marking_it_as_read() throws Throwable {
//        database = new Database("lukuvinkkikirjasto.db");
//        kirjaDAO = new DbKirjaDAO(database);
//        kommenttiDAO = new DbKommenttiDAO(database);
//        blogiDAO = new DbBlogiDAO(database);
//        videoDAO = new DbVideoDAO(database);
//        dao = new MasterDAO(kirjaDAO, blogiDAO, kommenttiDAO, videoDAO);
//        inputLines = new String[100];
//        inputLines[0] = "2";
//        inputLines[1] = "1";
//    }
//
//    @When("^marked as read$")
//    public void marked_as_read() throws Throwable {
//        inputLines[2] = "m";
//        inputLines[3] = "";
//        inputLines[4] = "";
//        io = new StubIO(inputLines);
//        ui = new TextUI(io, dao);
//	ui.run();
//    }
//
//    @Then("^user can see it is marked as read$")
//    public void user_can_see_it_is_marked_as_read() throws Throwable {
//        ArrayList<String> outputs = io.getOutputs();
//        int lastIndex = outputs.size() - 1;
//        /*
//        File file = new File("outputs.txt");
//        file.createNewFile();
//        FileWriter writer = new FileWriter(file);
//        for (int i = 0; i <= lastIndex; i++) {
//            writer.write(i+":"+outputs.get(i)+"\n");
//        }
//        writer.flush();
//        writer.close();
//        */
//        assertTrue(outputs.get(lastIndex - 8).
//                contains("Jackson, Shirley     | Onhan noita linnassa | 666                  | kyllä"));
//    }
    @Given("^list view is selected$")
    public void list_view_is_selected() {
        inputLines.add("2");

    }

    @Given("^a Lukuvinkki is selected$")
    public void a_Lukuvinkki_is_selected() throws Throwable {
        inputLines.add("1");
    }

    @Given("^a comment is given with first line \"([^\"]*)\" and second line \"([^\"]*)\"$")
    public void a_comment_is_given_with_first_line_and_second_line(String arg1, String arg2) throws Throwable {
        inputLines.add("u");
        inputLines.add("jokunimi");
        inputLines.add(arg1);
        inputLines.add(arg2);
        inputLines.add("");
    }

    @Then("^the comment is shown on first line \"([^\"]*)\" and second line \"([^\"]*)\"$")
    public void the_comment_is_shown_on_on_first_line_and_second_line(String arg1, String arg2) throws Throwable {
        runTestUI();
        for (int i = 0; i < io.getOutputs().size(); i++) {
            if (io.getOutputs().get(i).contains(arg1) && io.getOutputs().get(i).contains(arg2)) {
                assertTrue(true);
                return;
            }
        }
        assertTrue(false);
    }

    @When("^create new lukuvinkki is selected$")
    public void create_new_lukuvinkki_is_selected() throws Throwable {
        inputLines.add("1");
    }

    @Then("^\"([^\"]*)\" is available in the menu$")
    public void lukuvinkki_is_available_in_the_menu(String lukuvinkki_name) throws Throwable {
        runTestUI();
        int i = getListIndex(io.getOutputs(), "Valitse lisättävä tyyppi");

        assertTrue(io.getOutputs().get(i + 1).contains("=" + lukuvinkki_name));
    }

    @Given("^creating a new podcast is selected$")
    public void creating_a_new_podcast_is_selected() throws Throwable {
        inputLines.add("4");
    }

    @Given("^creating a new video is selected$")
    public void creating_a_new_video_is_selected() throws Throwable {
        inputLines.add("3");
    }

    @Given("^author \"([^\"]*)\" a random title and URL \"([^\"]*)\" are entered$")
    public void author_a_random_title_and_URL_are_entered(String author, String url) throws Throwable {
        randomTitle = UUID.randomUUID().toString();
        inputLines.add(randomTitle);
        inputLines.add(author);
        inputLines.add(url);
    }

    @Given("^author \"([^\"]*)\", title \"([^\"]*)\" and URL \"([^\"]*)\" are entered$")
    public void author_a_random_title_and_URL_are_entered(String author, String title, String url) throws Throwable {
        inputLines.add(title);
        inputLines.add(author);
        inputLines.add(url);
    }

    @When("^all items are listed$")
    public void all_items_are_listed() throws Throwable {
        inputLines.add("2");
    }

    @Then("^the random title appears on the list$")
    public void the_random_title_appears_on_the_list() throws Throwable {
        runTestUI();
        assertTrue(outputsContains(io, randomTitle.substring(0, 19)));
    }

    @Then("^the created lukuvinkki with author \"([^\"]*)\", title \"([^\"]*)\" appers on the list$")
    public void the_created_lukuvinkki_with_author_title_appers_on_the_list(String author, String title) throws Throwable {
        runTestUI();

        int i = getListIndex(io.getOutputs(), title);
        assertTrue(io.getOutputs().get(i).contains(title));
        assertTrue(io.getOutputs().get(i).contains(author));
    }

    @Given("^a lukuvinkki is added")
    public void add_lukuvinkki() {
        inputLines.add("1");
        inputLines.add("1");
        inputLines.add("testi");
        inputLines.add("testinen");
        inputLines.add("1234");
    }

    @Given("^correct data can be seen$")
    public void correct_data_in_list() throws SQLException {
        runTestUI();
        assertTrue(outputsContains(io, new Kirja("testi", "testinen", "1234").toString()));
    }

    @Given("^\"tagit\" is selected$")
    public void tagit_is_selected() {
        inputLines.add("5");
    }

    @Given("^a new tag is added$")
    public void new_tag_added() {
        inputLines.add("1");
        inputLines.add("tagi");
    }

    @Then("^that tag can be seen in the list$")
    public void that_tag_can_be_seen_in_the_list() throws SQLException {
        runTestUI();

        assertTrue(outputsContains(io, "tagi"));
    }

    boolean outputsContains(StubIO io, String string) {
        for (String line : io.getOutputs()) {
            if (line.contains(string)) {
                return true;
            }
        }
        return false;
    }

    int getListIndex(ArrayList<String> list, String string) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).contains(string)) {
                return i;
            }
        }
        return -1;
    }

    void runTestUI() throws SQLException {
        inputLines.add("");
        inputLines.add("");
        inputLines.add("");
        io = new StubIO(inputLines.toArray(new String[]{}));
        ui = new TextUI(io, dao);
        ui.run();
    }

}
