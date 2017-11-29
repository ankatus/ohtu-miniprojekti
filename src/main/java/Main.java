import data_access.Database;
import data_access.DbKirjaDAO;
import java.sql.SQLException;

import data_access.StubBlogiDAO;
import data_access.StubKommenttiDAO;
import user_interface.TerminalIO;
import user_interface.TextUI;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Database database = new Database("lukuvinkkikirjasto.db");
        DbKirjaDAO kirjaDAO = new DbKirjaDAO(database);
        StubKommenttiDAO kommenttiDAO = new StubKommenttiDAO();

        TextUI ui = new TextUI(new TerminalIO(), kirjaDAO, new StubBlogiDAO(), new StubKommenttiDAO());
		ui.run();
	}
}