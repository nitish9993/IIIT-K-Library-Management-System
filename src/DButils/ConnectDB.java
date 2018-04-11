
package DButils;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ConnectDB {
    
    public static Connection getConnections() throws SQLException{
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:LibraryM.sqlite");
            return conn;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        return null;
    }
}
