package data_access;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    private final String databaseAddress;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;

    }

    public Connection connect() {
        Connection connection = null;
        
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseAddress);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
        
        return connection;
    }
    
    public ResultSet executeQuerySelect(String query) {
        try {
            Connection connection = this.connect();
            
            PreparedStatement stmt = connection.prepareStatement(query);
            
            ResultSet rS = stmt.executeQuery();

            return rS;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return null;
    }
    
    public void executeQueryInsert(String query, List values) {
        try {
            Connection connection = this.connect();
            
            PreparedStatement stmt = connection.prepareStatement(query);
            
            stmt.setObject(1, values.get(0));
            stmt.setObject(2, values.get(1));
            stmt.setObject(3, values.get(2));
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
