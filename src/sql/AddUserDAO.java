package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddUserDAO {

	public boolean addUser(String user,String pass,String name,int type) {
		String sql = "insert into user values(?,?,?,?);";
		try {
		    Connection conn = MySQLLink.getMySQLLink().getConnection();
		    PreparedStatement ps = conn.prepareStatement(sql);
		    ps.setString(1, user);
		    ps.setString(2, pass);
		    ps.setString(3, name);
		    ps.setInt(4, type);
		    ps.executeUpdate();
		    return true;
		} catch (SQLException ex) {
		    Logger.getLogger(AdminMainDAO.class.getName()).log(Level.SEVERE, null, ex);
		    return false;
		}
	}
}
