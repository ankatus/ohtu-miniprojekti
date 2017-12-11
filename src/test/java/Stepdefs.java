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
import java.util.List;
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
    
    
    @Given("^system is asking input from user$")
    public void system_is_asking_input_from_user() throws Throwable {
        
        database = new Database("lukuvinkkikirjasto.db");
        kirjaDAO = new DbKirjaDAO(database);
        kommenttiDAO = new DbKommenttiDAO(database);
        blogiDAO = new DbBlogiDAO(database);
        videoDAO = new DbVideoDAO(database);
        dao = new MasterDAO(kirjaDAO, blogiDAO, kommenttiDAO, videoDAO);
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
        dao = new MasterDAO(kirjaDAO, blogiDAO, kommenttiDAO, videoDAO);
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
    
}
