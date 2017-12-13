package data_access;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    private final String databaseAddress;
    private Connection connection;
    private PreparedStatement stmt;
    private ResultSet rS;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;

        if (!checkIfDatabaseExists(databaseAddress)) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            createDatabase(databaseAddress);
        }
    }

    public boolean checkIfDatabaseExists(String databaseName) {
        File f = new File(databaseName);
        return f.exists();
    }

    public void createDatabase(String databaseName) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseAddress);
            
            executeQueryUpdate("CREATE TABLE Kommentti(\n"
                    + "lukuvinkki TEXT, \n"
                    + "kommentoija TEXT,\n"
                    + "kommentti TEXT,\n"
                    + "aika DATETIME DEFAULT (strftime('%H:%M %d.%m.%Y','now', 'localtime'))\n"
                    + ");", new ArrayList());
                    
            executeQueryUpdate("CREATE TABLE Kirja (\n"
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + "otsikko VARCHAR(100) NOT NULL,\n"
                    + "kirjoittaja VARCHAR(100),\n"
                    + "isbn VARCHAR(100),\n"
                    + "luettu BOOLEAN NOT NULL DEFAULT 0\n"
                    + ");", new ArrayList());
            
            executeQueryUpdate("CREATE TABLE Blogi (\n"
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + "otsikko VARCHAR(100) NOT NULL,\n"
                    + "kirjoittaja VARCHAR(100),\n"
                    + "url VARCHAR(100),\n"
                    + "luettu BOOLEAN NOT NULL DEFAULT 0\n"
                    + ");", new ArrayList());
            
            executeQueryUpdate("CREATE TABLE Video (\n"
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + "otsikko VARCHAR(100) NOT NULL,\n"
                    + "tekija VARCHAR(100),\n"
                    + "url VARCHAR(100),\n"
                    + "katsottu BOOLEAN NOT NULL DEFAULT 0\n"
                    + ");", new ArrayList());
            
            executeQueryUpdate("CREATE TABLE Podcast (\n"
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + "otsikko VARCHAR(100) NOT NULL,\n"
                    + "tekija VARCHAR(100),\n"
                    + "url VARCHAR(100),\n"
                    + "kuunneltu BOOLEAN NOT NULL DEFAULT 0\n"
                    + ");", new ArrayList());
            
            executeQueryUpdate("CREATE TABLE Tag (\n"
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + "tag VARCHAR(100) NOT NULL\n"
                    + ");", new ArrayList());
            
            executeQueryUpdate("CREATE TABLE Taglink (\n"
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + "lukuvinkki TEXT,\n"
                    + "tagid INTEGER,\n"
                    + "FOREIGN KEY(tagid) REFERENCES Tag(id)\n"
                    + ");", new ArrayList());
            
            closeConnection();
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public Connection connect() {
        connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseAddress);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return connection;
    }

    public void closeConnection() {
        try {
            if (rS != null) {
                rS.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ResultSet executeQuerySelect(String query, List values) {
        try {
            connection = this.connect();

            stmt = connection.prepareStatement(query);

            int i = values.size();
            for (int j = 0; j < i; j++) {
                stmt.setObject(j + 1, values.get(j));
            }

            rS = stmt.executeQuery();

            return rS;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    public void executeQueryUpdate(String query, List values) {
        try {
            connection = this.connect();

            stmt = connection.prepareStatement(query);

            int i = values.size();
            for (int j = 0; j < i; j++) {
                stmt.setObject(j + 1, values.get(j));
            }

            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
