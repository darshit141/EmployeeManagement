package com.manage.Entity;

public class FilterAPI {
	private long pno=0;			   
	private String department = "";
	private String position = "";
	
	
	public long getPno() {
		return pno;
	}

	public void setPno(long pno) {
		this.pno = pno;
	}

	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}

	public FilterAPI(String department, String position) {
		super();

		if(department != null) {
			this.department = department ;
		}
		if(position != null) {
			this.position = position ;
		}
		

	}

	
 
}
