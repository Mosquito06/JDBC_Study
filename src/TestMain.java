

import java.util.List;

import javax.swing.JOptionPane;

import JDBC_Study.dao.DepartmentDao;
import JDBC_Study.dto.Department;

public class TestMain {

	public static void main(String[] args) {
		/*DBCon dbCon = DBCon.getInstance();
		System.out.println(dbCon);
		System.out.println(dbCon.getConn());
		
		
		
		DBCon dbCon1 = DBCon.getInstance();
		System.out.println(dbCon1);
		System.out.println(dbCon1.getConn());

		dbCon.connCloser();*/
	
		DepartmentDao dao = DepartmentDao.getInstance();
		//showDepartmentList(dao);
		
		Department dept = new Department(1, "¸¶ÄÉÆÃ", 10);
		/*dao.insertDepartment(dept);
		showDepartmentList(dao);
		
		dao.deleteDepartment(dept);
		showDepartmentList(dao);*/
		
		/*Department dept2 = dao.selectDepartmentByNo(dept);
		JOptionPane.showMessageDialog(null, dept2);*/
		
		dao.updateDepartment(dept);
		showDepartmentList(dao);
	}

	private static void showDepartmentList(DepartmentDao dao) {
		List<Department> lists = dao.selectDepartmentByAll();
		for(Department dept: lists){
			System.out.println(dept);
		}
	}

}
