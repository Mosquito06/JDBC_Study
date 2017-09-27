package JDBC_Study.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class eDBCon {
	private static final eDBCon instance = new eDBCon();
	
	private String user = "root";
	private String password = "rootroot";
	private String url = "jdbc:mysql://localhost/mysql_study";
	private Connection con;

	public static eDBCon getInstance() {
		return instance;
	}

	public Connection getCon() {
		return con;
	}

	public eDBCon() {
		try {
			con = DriverManager.getConnection(url, user, password);
			JOptionPane.showMessageDialog(null, "연결이 완료되었습니다");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void conClose(){
		jdbcUtil.close(con);
	}

}
