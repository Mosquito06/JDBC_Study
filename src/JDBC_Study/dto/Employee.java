package JDBC_Study.dto;

public class Employee {
	private int empNo;
	private String empName;
	private String title;
	private int manager;
	private int salary;
	private int dno;
	private String leaveOffice;

	public Employee(int empNo) {
		this.empNo = empNo;
	}

	public Employee(int empNo, String empName, String title, int manager, int salary, int dno, String leaveOffice) {
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.manager = manager;
		this.salary = salary;
		this.dno = dno;
		this.leaveOffice = leaveOffice;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getManager() {
		return manager;
	}

	public void setManager(int manager) {
		this.manager = manager;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getDno() {
		return dno;
	}

	public void setDno(int dno) {
		this.dno = dno;
	}

	public String getLeaveOffice() {
		return leaveOffice;
	}

	public void setLeaveOffice(String leaveOffice) {
		this.leaveOffice = leaveOffice;
	}

	@Override
	public String toString() {
		return String.format("사원번호: %s, 사원명 : %s, 직책 : %s, 상사 : %s, 월급 : %s, 부서번호: %s, 퇴근 시간: %s",
				empNo, empName, title, manager, salary, dno, leaveOffice);
	}
	
	
	
}
