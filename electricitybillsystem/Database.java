
package electricitybillsystem;

import java.sql.*;

public class Database {

    Connection c;
    Statement s;

    Database() {
        try {
            c = DriverManager.getConnection("jdbc:mysql:///ebms3", "root", "samarthkharadegps");
            s = c.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
