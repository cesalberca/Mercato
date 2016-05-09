package es.cesalberca.mercato.controller.auth;

import es.cesalberca.mercato.controller.database.DatabaseHandler;
import es.cesalberca.mercato.controller.database.Sqlite;
import es.cesalberca.mercato.model.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author César Alberca
 */
public class Login {
    public static int tries = 3;
    
    public static Boolean isValidUser(User userTryingToLogin) throws SQLException, ClassNotFoundException {
        ResultSet rs;
        User user = null;
        Object db = DatabaseHandler.connect();
        
        if (db instanceof Sqlite) {
            Connection c = ((Sqlite) db).getConnection();
            rs = db.search(c, userTryingToLogin);
        }
        
        while (rs.next()) {
            user = new User(rs.getString("NAME"), rs.getString("PASSWORD"));
        }
        
        sqlite.disconnect(c);
        
        if (user != null && userTryingToLogin.getPassword().equals(user.getPassword())) {
            return true;
        } else if (tries > 0) {
            tries--;
            return false;
        } else {
            return false;
        }
    }
}
