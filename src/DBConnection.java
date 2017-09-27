import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class Student { // dto 클래스 작성(원래는 다른 클래스에서 작성해야함)
	private String id;
	private String name;
	private String dept;

	public Student(String id, String name, String dept) {
		this.id = id;
		this.name = name;
		this.dept = dept;
	}

	public Student(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return String.format("Student [id=%s, name=%s, dept=%s]", id, name, dept);
	}
	

}

public class DBConnection {

	private static Connection con;

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/sampledb?autoReconnect=true&useSSL=false";
		String user = "user_sampledb";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("드라이버를 로딩하였습니다.");

			con = DriverManager.getConnection(url, user, "");
			System.out.println("Database에 연결 성공");

			/*showList(con);
			insertStudent(con, "1234567", "홍길동", "활빈당");
			showList(con);

			Student updateStd = new Student("1234567", "홍길동", "개혁당");
			updateStudent(con, updateStd);
			showList(con);

			Student delStd = new Student("홍길동");
			deleteStudent(con, delStd);
			showList(con);*/

			// 아래 방법은 잘 사용안하고 반복문으로 결과 출력
			/*
			 * boolean res = rs.first(); 
			 * System.out.println(res? "첫 레코드로 이동":* "데이터 없음");
			 * 
			 * String id = rs.getString(1); // 속성(컬럼)의 인덱스는 1부터 시작 
			 * String name = rs.getString("name"); // 속성명으로도 접근 가능 String dept =
			 * rs.getString(3); 
			 * System.out.printf("id : %s, name : %s, dept : %s%n", id, name, dept);
			 * 
			 * res = rs.last();
			 * 
			 * System.out.println(res? "마지막 레코드로 이동": "데이터 없음"); 
			 * id = rs.getString(1); // 속성(컬럼)의 인덱스는 1부터 시작 name =
			 * rs.getString("name"); // 속성명으로도 접근 가능 
			 * dept = rs.getString(3);
			 * System.out.printf("id : %s, name : %s, dept : %s%n", id, name, dept);
			 * 
			 * rs.absolute(1); 
			 * while(rs.next()){ 
			 * id = rs.getString(1); // 속성(컬럼)의 인덱스는 1부터 시작 
			 * name = rs.getString("name"); // 속성명으로도 접근 가능
			 * dept = rs.getString(3); 
			 * System.out.printf( "id : %s, name : %s, dept : %s%n", id, name, dept);
			 *  }
			 */

			 List<Student> stdList = getStudents(con); 
			 for(Student s : stdList){ 
				 System.out.println(s);
			}
			 
		} catch (ClassNotFoundException e) {
			System.err.println("jdbc Driver가 존재하지 않습니다.");

		} catch (SQLException e) {
			System.err.println("DataBase 연결 오류 url 혹은 user 및  password를 확인");
			System.err.printf("error Code : %s, error Message : %s%n", e.getErrorCode(), e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				// 마지막으로 커넥션 종료
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static List<Student> getStudents(Connection con) throws SQLException {
		ArrayList<Student> lists = new ArrayList<>();
		String sql = "select id, name, dept from student";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			lists.add(new Student(rs.getString(1), rs.getString(2), rs.getString(3)));
		}

		return lists;
	}

	private static void deleteStudent(Connection con, Student delStd) throws SQLException {
		String sql = "delete from student where name = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);

		System.out.println(delStd.getName());
		
		pstmt.setString(1, delStd.getName()); 
		int res = pstmt.executeUpdate();
		
		//System.out.println(pstmt);
	
		 System.out.println(res + "개 삭제"); 
		 pstmt.close();
		 
	}

	private static void updateStudent(Connection con, Student updateStd) throws SQLException {
		String sql = "update student set dept = ? where name = ? and id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, updateStd.getDept());
		pstmt.setString(2, updateStd.getName());
		pstmt.setString(3, updateStd.getId());
		int res = pstmt.executeUpdate();
		System.out.println(res + "개 수정");
		pstmt.close();
	}

	private static void insertStudent(Connection con, String id, String name, String dept) throws SQLException {
		String sql = "insert into student values(?,?,?)";
		// Statement가 완성되지 않았다는 의미에서 prepareStatement 클래스임
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, name);
		pstmt.setString(3, dept);

		// System.out.println(pstmt);
		int res = pstmt.executeUpdate();
		System.out.println(res + "개 삽입");
		// 반듯이 종료를 해야함
		pstmt.close();
	}

	private static void showList(Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		String sql = "select id, name, dept from student";
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			String id = rs.getString(1); // 속성(컬럼)의 인덱스는 1부터 시작
			String name = rs.getString("name"); // 속성명으로도 접근 가능
			String dept = rs.getString(3);
			System.out.printf("id : %s, name : %s, dept : %s%n", id, name, dept);
		}
		// 역으로 종료
		rs.close();
		stmt.close();
	}

}
