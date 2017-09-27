

import java.util.List;

import javax.swing.JOptionPane;

import JDBC_Study.dao.DepartmentDao;
import JDBC_Study.dto.Department;
import JDBC_Study.jdbc.DBCon;

public class TestMain {

	public static void main(String[] args) {
		/*DBCon dbCon = DBCon.getInstance();
		System.out.println(dbCon);
		System.out.println(dbCon.getConn());
		
		
		
		DBCon dbCon1 = DBCon.getInstance();
		System.out.println(dbCon1);
		System.out.println(dbCon1.getConn());

		dbCon.connCloser();*/
	
		Department();
		
		
		
		DBCon.getInstance().connCloser();
		
	}

	private static void Department() {
		DepartmentDao dao = DepartmentDao.getInstance();
		//showDepartmentList(dao);
		
		Department dept = new Department(5, "생산", 0);
		dao.insertDepartment(dept);
		showDepartmentList(dao);
		System.out.println("=================================================");
		
		dept.setDeptName("신제품기획");
		dao.updateDepartment(dept);
		showDepartmentList(dao);
		System.out.println("=================================================");
		
		Department dept2 = dao.selectDepartmentByNo(dept);
		JOptionPane.showMessageDialog(null, dept2);
				
		dao.deleteDepartment(dept);
		showDepartmentList(dao);
		System.out.println("=================================================");
	}

	private static void showDepartmentList(DepartmentDao dao) {
		List<Department> lists = dao.selectDepartmentByAll();
		for(Department dept: lists){
			System.out.println(dept);
		}
	}

}
