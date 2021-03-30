package sql;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminMainDAO {
	
	public ResultSet getAllUser() {
		String sql = "select * from user;";
		try {
		    Connection conn = MySQLLink.getMySQLLink().getConnection();
		    PreparedStatement ps = conn.prepareStatement(sql);
		    ResultSet rs = ps.executeQuery();
		    return rs;
		} catch (SQLException ex) {
		    Logger.getLogger(AdminMainDAO.class.getName()).log(Level.SEVERE, null, ex);
		    return null;
		}
	}
	
	public boolean deleteUser(String user) {
		String sql = "delete from user where user=?;";
		try {
		    Connection conn = MySQLLink.getMySQLLink().getConnection();
		    PreparedStatement ps = conn.prepareStatement(sql);
		    ps.setString(1, user);
		    ps.executeUpdate();
		    return true;
		} catch (SQLException ex) {
		    Logger.getLogger(AdminMainDAO.class.getName()).log(Level.SEVERE, null, ex);
		    return false;
		}
	}
}
