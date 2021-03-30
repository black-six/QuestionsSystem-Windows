package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginDAO {

	public int findUser(String user,String pass) {
		String sql = "select * from user where user=?;";
		try {
		    Connection conn = MySQLLink.getMySQLLink().getConnection();
		    PreparedStatement ps = conn.prepareStatement(sql);
		    ps.setString(1, user);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		    	if (rs.getString(2).equals(pass)) {
		    		if (rs.getInt(4) == 0) {
						return 1;
					} else {
						return -1;
					}
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		} catch (SQLException ex) {
		    Logger.getLogger(AdminMainDAO.class.getName()).log(Level.SEVERE, null, ex);
		    return 0;
		}
	}
}
