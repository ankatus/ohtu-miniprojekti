import data_access.*;

import java.sql.SQLException;

import user_interface.TerminalIO;
import user_interface.TextUI;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //System.setProperty("file.encoding", "UTF-8");   
        Database database = new Database("lukuvinkkikirjasto.db");
        DbKirjaDAO kirjaDAO = new DbKirjaDAO(database);
        DbKommenttiDAO kommenttiDAO = new DbKommenttiDAO(database);
        DbBlogiDAO blogiDAO = new DbBlogiDAO(database);
        TextUI ui = new TextUI(new TerminalIO(), kirjaDAO, blogiDAO, kommenttiDAO);
        ui.run();
    }
}
