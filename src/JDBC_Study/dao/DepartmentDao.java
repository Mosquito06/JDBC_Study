package JDBC_Study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import JDBC_Study.dto.Department;
import JDBC_Study.jdbc.DBCon;
import JDBC_Study.jdbc.jdbcUtil;

public class DepartmentDao {
	private static final DepartmentDao instance = new DepartmentDao();

	private String sql;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Connection con;

	private DepartmentDao() {
	}

	public static DepartmentDao getInstance() {
		return instance;
	}

	public List<Department> selectDepartmentByAll() {
		List<Department> lists = new ArrayList<>();
		sql = "select deptno, deptname, floor from department";
		con = DBCon.getInstance().getConn();

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int deptNo = rs.getInt("deptno");
				String deptName = rs.getString("deptname");
				int floor = rs.getInt("floor");
				lists.add(new Department(deptNo, deptName, floor));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
		}

		return lists;
	}

	public Department selectDepartmentByNo(Department dept) {
		Department department = null;
		sql = "select deptno, deptname, floor from department where deptno = ?";
		try {
			pstmt = DBCon.getInstance().getConn().prepareStatement(sql);
			pstmt.setInt(1, dept.getDeptNO());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				department = new Department(rs.getInt("deptNO"), rs.getString("deptName"),rs.getInt("floor"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return department;
	}

	public void insertDepartment(Department dept) {
		sql = "insert into department values(?,?,?)";
		try {
			pstmt = DBCon.getInstance().getConn().prepareStatement(sql);
			pstmt.setInt(1, dept.getDeptNO());
			pstmt.setString(2, dept.getDeptName());
			pstmt.setInt(3, dept.getFloor());
			
			int res = pstmt.executeUpdate();
			if(res < 0){
				System.out.println("삽입 실패");
				return;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void updateDepartment(Department dept) {
		sql = "update department set deptname = '마케팅' where deptNo = ?";
		try {
			pstmt = DBCon.getInstance().getConn().prepareStatement(sql);
			pstmt.setInt(1, dept.getDeptNO());
			
			int res = pstmt.executeUpdate();
			if(res > 0)
			{
				JOptionPane.showMessageDialog(null, "수정을 완료하였습니다.");
			}else{
				JOptionPane.showMessageDialog(null, "수정을 완료하지 못했습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteDepartment(Department dept) {
		sql = "delete from department where deptNo = ?";
		try {
			pstmt = DBCon.getInstance().getConn().prepareStatement(sql);
			pstmt.setInt(1, dept.getDeptNO());
			
			int res = pstmt.executeUpdate();
			if(res > 0)
			{
				JOptionPane.showMessageDialog(null, "삭제를 완료하였습니다.");
			}else{
				JOptionPane.showMessageDialog(null, "삭제를 완료하지 못했습니다.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
