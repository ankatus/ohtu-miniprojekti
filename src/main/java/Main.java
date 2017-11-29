import data_access.BlogiDAO;
import data_access.Database;
import data_access.DbBlogiDAO;
import data_access.DbKirjaDAO;
import java.sql.SQLException;

import data_access.StubBlogiDAO;
import data_access.StubKommenttiDAO;
import user_interface.TerminalIO;
import user_interface.TextUI;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //System.setProperty("file.encoding", "UTF-8");
        Database database = new Database("lukuvinkkikirjasto.db");
        DbKirjaDAO kirjaDAO = new DbKirjaDAO(database);
        StubKommenttiDAO kommenttiDAO = new StubKommenttiDAO();
        DbBlogiDAO blogiDAO = new DbBlogiDAO(database);

        TextUI ui = new TextUI(new TerminalIO(), kirjaDAO, blogiDAO, new StubKommenttiDAO());
		ui.run();
	}
}