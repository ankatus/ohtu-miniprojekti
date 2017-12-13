import org.junit.Before;
import user_interface.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import data_access.Database;
import data_access.DbBlogiDAO;
import data_access.DbKirjaDAO;
import data_access.DbKommenttiDAO;
import data_access.DbVideoDAO;
import data_access.MasterDAO;
/*
import java.io.File;
import java.io.FileWriter;
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    


    @cucumber.api.java.Before
    public void setUp() throws ClassNotFoundException {
        inputLines = new ArrayList<>();
        database = new Database("testdb.db");
        kirjaDAO = new DbKirjaDAO(database);
        kommenttiDAO = new DbKommenttiDAO(database);
        blogiDAO = new DbBlogiDAO(database);
        videoDAO = new DbVideoDAO(database);
        dao = new MasterDAO(kirjaDAO, blogiDAO, kommenttiDAO, videoDAO);
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



    @Given("^list view is entered$")
    public void list_view_is_entered() {
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
        inputLines.add("");
        inputLines.add("");
        inputLines.add("");
        inputLines.add("");//jotta ohjelma sulkeutuu...
        io = new StubIO(inputLines.toArray(new String[]{}));
        TextUI ui = new TextUI(io, dao);
        ui.run();
        for (int i = 0; i < io.getOutputs().size(); i++) {
            if (io.getOutputs().get(i).contains(arg1) && io.getOutputs().get(i).contains(arg2)) {
                assertTrue(true);
                return;
            }
        }
        assertTrue(false);
    }


}
