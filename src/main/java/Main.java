
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
        DbVideoDAO videoDAO = new DbVideoDAO(database);
        DbPodcastDAO podcastDAO = new DbPodcastDAO(database);
        DbTagDAO tagDAO = new DbTagDAO(database);
        MasterDAO dao = new MasterDAO(kirjaDAO, blogiDAO, kommenttiDAO, videoDAO, podcastDAO, tagDAO);

        TextUI ui = new TextUI(new TerminalIO(), dao);
        ui.run();
    }
}
