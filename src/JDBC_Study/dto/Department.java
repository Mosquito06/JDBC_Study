package JDBC_Study.dto;

public class Department {
	private int deptNO;
	private String deptName;
	private int floor;

	public Department(int deptNO) {
		this.deptNO = deptNO;
	}

	public Department(int deptNO, String deptName, int floor) {
		this.deptNO = deptNO;
		this.deptName = deptName;
		this.floor = floor;
	}

	public int getDeptNO() {
		return deptNO;
	}

	public void setDeptNO(int deptNO) {
		this.deptNO = deptNO;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	@Override
	public String toString() {
		return String.format("부서번호 : %s, 부서명 : %s, 위치 : %s층]", deptNO, deptName, floor);
	}

}
