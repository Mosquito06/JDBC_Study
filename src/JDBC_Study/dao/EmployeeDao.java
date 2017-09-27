package JDBC_Study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBC_Study.dto.Employee;
import JDBC_Study.jdbc.eDBCon;
import JDBC_Study.jdbc.jdbcUtil;

public class EmployeeDao {

	private static final EmployeeDao instance = new EmployeeDao();

	private String sql;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Connection con;

	private EmployeeDao() {

	}

	public static EmployeeDao getInstance() {
		return instance;
	}

	public List<Employee> selectEmployeeByAll() {
		List<Employee> lists = new ArrayList<>();
		sql = "select empno, empname, title, manager, salary, dno, leaveoffice from employee";
		con = eDBCon.getInstance().getCon();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int empNo = rs.getInt("empNo");
				String empName = rs.getString("empName");
				String title = rs.getString("title");
				int manager = rs.getInt("manager");
				int salary = rs.getInt("salary");
				int dno = rs.getInt("dno");
				String leaveOffice = rs.getString("leaveOffice");
				lists.add(new Employee(empNo, empName, title, manager, salary, dno, leaveOffice));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
		}
		return lists;
	}

	public Employee selectEmployeeByNo(Employee e) {
		Employee employee = null;
		sql = "select empno, empname, title, manager, salary, dno, leaveoffice from employee where empno = ?";
		try {
			pstmt = eDBCon.getInstance().getCon().prepareStatement(sql);
			pstmt.setInt(1, e.getEmpNo());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				employee = new Employee(rs.getInt("empno"), rs.getString("empname"), rs.getString("title"), rs.getInt("manager"), rs.getInt("salary"), rs.getInt("dno"), rs.getString("leaveoffice"));
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}	finally{
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
		}

		return employee;
	}

}
