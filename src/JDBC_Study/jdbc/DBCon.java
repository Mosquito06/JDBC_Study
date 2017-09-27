package JDBC_Study.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {
	private String user = "root";
	private String password = "rootroot";
	private String url = "jdbc:mysql://localhost/mysql_study";
	private Connection conn;
	// DB 연결은 시간이 오래 걸리는 작업이기 때문에 여러번 생성하면 프로그램의 수행 시간이 느려짐.
	// 그렇기 때문에 DB 객체를 지속적으로 생성하지 못하도록 클래스의 필드에서 DB를 생성하고 생성자를 private으로 함.
	// 그리고 해당 필드를 static으로 하고, 해당 필드에 대해 get, set을 생성
	// 이러한 방식을 싱글턴 패턴이라 한다.
	// 싱글턴 패턴: 해당 클래스의 인스턴스가 하나만 만들어지고, 어디서든지 해당 인스턴스에 접근할 수 있도록 하기 위한 패턴
	private static final DBCon instance = new DBCon();
		
	public static DBCon getInstance() {
		return instance;
	}

	public Connection getConn() {
		return conn;
	}

	private DBCon() {
		try {
			conn = DriverManager.getConnection(url, user, password);

		} catch (SQLException e) {
			System.err.printf("%s - %s%n", e.getErrorCode(), e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void connCloser(){
		jdbcUtil.close(conn);
	}

}
