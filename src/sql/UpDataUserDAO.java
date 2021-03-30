package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpDataUserDAO {

	public boolean rePass(String user) {
		String sql = "update user set pass=123456 where user=?;";
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
	
	public boolean upDataUser(String user,String name,int type,String oldUser) {
		String sql = "update user set user=?,name=?,type=? where user=?;";
		try {
		    Connection conn = MySQLLink.getMySQLLink().getConnection();
		    PreparedStatement ps = conn.prepareStatement(sql);
		    ps.setString(1, user);
		    ps.setString(2, name);
		    ps.setInt(3, type);
		    ps.setString(4, oldUser);
		    ps.executeUpdate();
		    return true;
		} catch (SQLException ex) {
		    Logger.getLogger(AdminMainDAO.class.getName()).log(Level.SEVERE, null, ex);
		    return false;
		}
	}
}
