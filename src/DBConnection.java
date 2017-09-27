import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class Student { // dto Ŭ���� �ۼ�(������ �ٸ� Ŭ�������� �ۼ��ؾ���)
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
			System.out.println("����̹��� �ε��Ͽ����ϴ�.");

			con = DriverManager.getConnection(url, user, "");
			System.out.println("Database�� ���� ����");

			/*showList(con);
			insertStudent(con, "1234567", "ȫ�浿", "Ȱ���");
			showList(con);

			Student updateStd = new Student("1234567", "ȫ�浿", "������");
			updateStudent(con, updateStd);
			showList(con);

			Student delStd = new Student("ȫ�浿");
			deleteStudent(con, delStd);
			showList(con);*/

			// �Ʒ� ����� �� �����ϰ� �ݺ������� ��� ���
			/*
			 * boolean res = rs.first(); 
			 * System.out.println(res? "ù ���ڵ�� �̵�":* "������ ����");
			 * 
			 * String id = rs.getString(1); // �Ӽ�(�÷�)�� �ε����� 1���� ���� 
			 * String name = rs.getString("name"); // �Ӽ������ε� ���� ���� String dept =
			 * rs.getString(3); 
			 * System.out.printf("id : %s, name : %s, dept : %s%n", id, name, dept);
			 * 
			 * res = rs.last();
			 * 
			 * System.out.println(res? "������ ���ڵ�� �̵�": "������ ����"); 
			 * id = rs.getString(1); // �Ӽ�(�÷�)�� �ε����� 1���� ���� name =
			 * rs.getString("name"); // �Ӽ������ε� ���� ���� 
			 * dept = rs.getString(3);
			 * System.out.printf("id : %s, name : %s, dept : %s%n", id, name, dept);
			 * 
			 * rs.absolute(1); 
			 * while(rs.next()){ 
			 * id = rs.getString(1); // �Ӽ�(�÷�)�� �ε����� 1���� ���� 
			 * name = rs.getString("name"); // �Ӽ������ε� ���� ����
			 * dept = rs.getString(3); 
			 * System.out.printf( "id : %s, name : %s, dept : %s%n", id, name, dept);
			 *  }
			 */

			 List<Student> stdList = getStudents(con); 
			 for(Student s : stdList){ 
				 System.out.println(s);
			}
			 
		} catch (ClassNotFoundException e) {
			System.err.println("jdbc Driver�� �������� �ʽ��ϴ�.");

		} catch (SQLException e) {
			System.err.println("DataBase ���� ���� url Ȥ�� user ��  password�� Ȯ��");
			System.err.printf("error Code : %s, error Message : %s%n", e.getErrorCode(), e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				// ���������� Ŀ�ؼ� ����
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
	
		 System.out.println(res + "�� ����"); 
		 pstmt.close();
		 
	}

	private static void updateStudent(Connection con, Student updateStd) throws SQLException {
		String sql = "update student set dept = ? where name = ? and id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, updateStd.getDept());
		pstmt.setString(2, updateStd.getName());
		pstmt.setString(3, updateStd.getId());
		int res = pstmt.executeUpdate();
		System.out.println(res + "�� ����");
		pstmt.close();
	}

	private static void insertStudent(Connection con, String id, String name, String dept) throws SQLException {
		String sql = "insert into student values(?,?,?)";
		// Statement�� �ϼ����� �ʾҴٴ� �ǹ̿��� prepareStatement Ŭ������
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, name);
		pstmt.setString(3, dept);

		// System.out.println(pstmt);
		int res = pstmt.executeUpdate();
		System.out.println(res + "�� ����");
		// �ݵ��� ���Ḧ �ؾ���
		pstmt.close();
	}

	private static void showList(Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		String sql = "select id, name, dept from student";
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			String id = rs.getString(1); // �Ӽ�(�÷�)�� �ε����� 1���� ����
			String name = rs.getString("name"); // �Ӽ������ε� ���� ����
			String dept = rs.getString(3);
			System.out.printf("id : %s, name : %s, dept : %s%n", id, name, dept);
		}
		// ������ ����
		rs.close();
		stmt.close();
	}

}
