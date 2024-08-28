package models;



public class Header {
	private String userName;
	private String empName;
	private boolean deptStatus;
	private boolean loginStatus;
	
	public Header() {
		
	}
	public Header(String userName, String empName, boolean deptStatus, boolean loginStatus) {
		super();
		this.userName = userName;
		this.empName = empName;
		this.deptStatus = deptStatus;
		this.loginStatus = loginStatus;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public boolean isDeptStatus() {
		return deptStatus;
	}
	public void setDeptStatus(boolean deptStatus) {
		this.deptStatus = deptStatus;
	}
	public boolean isLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}

	
}
