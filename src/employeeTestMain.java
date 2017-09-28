import java.util.List;

import javax.swing.JOptionPane;

import JDBC_Study.dao.EmployeeDao;
import JDBC_Study.dto.Employee;

public class employeeTestMain {

	public static void main(String[] args) {
		EmployeeDao dao = EmployeeDao.getInstance();
		showEmployeeTable(dao);
		System.out.println("==============================================");
		
		Employee e2 = new Employee(4008, "������", "���", 1003, 1500000, 4, "7�����");
		dao.insertDepartment(e2);
		showEmployeeTable(dao);
		System.out.println("==============================================");
		
		Employee e = new Employee(4008);
		JOptionPane.showMessageDialog(null, dao.selectEmployeeByNo(e));
		
		e2.setTitle("�븮");
		dao.updateDepartment(e2);
		showEmployeeTable(dao);
		System.out.println("==============================================");
		
		dao.deleteDepartment(e2);
		showEmployeeTable(dao);
	}

	private static void showEmployeeTable(EmployeeDao dao) {
		List<Employee> lists = dao.selectEmployeeByAll();
		for (Employee e : lists) {
			System.out.println(e);
		}
	}

}
