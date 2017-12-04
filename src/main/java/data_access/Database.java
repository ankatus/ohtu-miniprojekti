package data_access;

import java.sql.*;
import java.util.List;

public class Database {

    private final String databaseAddress;
    private Connection connection;
    private PreparedStatement stmt;
    private ResultSet rS;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;

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
            for (int j = 0; j<i; j++) {
                stmt.setObject(j+1, values.get(j));
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
            for (int j = 0; j<i; j++) {
                stmt.setObject(j+1, values.get(j));
            }
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
