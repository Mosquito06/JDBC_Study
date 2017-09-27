import java.util.List;

import javax.swing.JOptionPane;

import JDBC_Study.dao.EmployeeDao;
import JDBC_Study.dto.Employee;

public class employeeTestMain {

	public static void main(String[] args) {
		EmployeeDao dao = EmployeeDao.getInstance();
		showEmployeeTable(dao);
		
		Employee e = new Employee(1004);
		JOptionPane.showMessageDialog(null, dao.selectEmployeeByNo(e));
		
		
		
	}

	private static void showEmployeeTable(EmployeeDao dao) {
		List<Employee> lists = dao.selectEmployeeByAll();
		for(Employee e : lists){
			System.out.println(e);
		}
	}

}
