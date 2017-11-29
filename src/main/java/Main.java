import data_access.Database;
import data_access.DbBlogiDAO;
import data_access.DbKirjaDAO;
import java.sql.SQLException;
import user_interface.TerminalIO;
import user_interface.TextUI;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
            Database database = new Database("lukuvinkkikirjasto.db");
            DbKirjaDAO kirjaDAO = new DbKirjaDAO(database);
            DbBlogiDAO blogiDAO = new DbBlogiDAO(database);
            TextUI ui = new TextUI(new TerminalIO(), kirjaDAO, blogiDAO);
            ui.run();
	}
}