package com.manage.Dao;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.manage.Entity.Employee;
import com.manage.Entity.FilterAPI;


@Repository

public class EmpDao implements EmpInterface {

	private static final String Insert_Employee_Query = "Insert INTO employee(id,first_name, last_name, salary, department, position, email_address,contact_number, picture) values(?,?,?,?,?,?,?,?,?)";

	private static final String Update_Employee_Query = "UPDATE EMPLOYEE SET first_name=?, last_name=?, salary=?, department=?, position=?, email_address=?,contact_number=?, picture=? WHERE ID=?";

	private static final String Get_Employee_Query = "SELECT * FROM EMPLOYEE WHERE ID =?";

	private static final String Delete_Employee_By_Id_Query = "DELETE FROM EMPLOYEE WHERE ID=?";

	private static final String Get_AllEmployee_Query = "SELECT * FROM employee ORDER BY id ASC LIMIT 3 OFFSET ? ";

	private static final String Filter_By_Department="SELECT * FROM EMPLOYEE WHERE department=?";
	
   private static final String  Filter_By_Position="SELECT * FROM EMPLOYEE WHERE position=?";
   
  private static final String  Pagination="SELECT * FROM employee ORDER BY id ASC LIMIT ? ";

  private static final String  Paging="SELECT * FROM employee ORDER BY id ASC LIMIT 3 OFFSET ? ";
  
//  private static final String Count="SELECT count(*) FROM EMPLOYEE";
	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public Employee getEmployeebyid(long id) {
	 
		
		return jdbc.queryForObject(Get_Employee_Query, (rs, rowNum) -> {
			return new Employee(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"),
					rs.getLong("salary"), rs.getString("department"), rs.getString("position"),
					rs.getString("email_address"), rs.getLong("contact_number"), rs.getString("picture"));
		},new Object[]{id});
	}
 
	@Override
	public List<Employee> getAllEmployee(long id) {
		
		 
		 
		return jdbc.query(Get_AllEmployee_Query, (rs, rowNum) -> {
			return new Employee(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"),
					rs.getLong("salary"), rs.getString("department"), rs.getString("position"),
					rs.getString("email_address"), rs.getLong("contact_number"), rs.getString("picture"));
		},new Object[]{id});

	}

	@Override
	public Employee saveEmployee(Employee employee, MultipartFile file ) throws IOException {
 
		
		jdbc.update(Insert_Employee_Query, employee.getId(), employee.getFirst_name(), employee.getLast_name(),
				employee.getSalary(), employee.getDepartment(), employee.getPosition(), employee.getEmail_address(),
				employee.getContact_number(), employee.getPicture());

		return employee;
	}

	@Override
	public Employee updatEmployee(Employee employee, MultipartFile file ) throws IOException   {
	
		
		
		jdbc.update(Update_Employee_Query, employee.getFirst_name(), employee.getLast_name(),
				employee.getSalary(), employee.getDepartment(), employee.getPosition(), employee.getEmail_address(),
				employee.getContact_number(),employee.getPicture(), employee.getId());

		return employee;
	}

	@Override
	public long deleteEmployee(long id) {
		jdbc.update(Delete_Employee_By_Id_Query, id);
		return id;
	}

	@Override
	public List<Employee> FilterEmployees(FilterAPI filter) {
	
		try {		
				int z= Integer.parseInt(filter.getPosition());
			
		
				if( z>0	)
				{
					System.out.println("inside Integer"+z);
					return jdbc.query(Pagination, (rs, rowNum) -> {
						return new Employee(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"),
								rs.getLong("salary"), rs.getString("department"), rs.getString("position"),
								rs.getString("email_address"), rs.getLong("contact_number"), rs.getString("picture"));
					},z);
				}
			}
		catch(Exception e)
			{
				System.out.println(e);
			}
		
			
				if(filter.getPosition()!= "")  
				{
			
			
					return jdbc.query(Filter_By_Position, (rs, rowNum) -> {
						return new Employee(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getLong("salary"), rs.getString("department"), rs.getString("position"),
						rs.getString("email_address"), rs.getLong("contact_number"), rs.getString("picture"));
					},filter.getPosition());
				}
				else if(filter.getDepartment()!= "") 
				{
			return jdbc.query(Filter_By_Department, (rs, rowNum) -> {
				return new Employee(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getLong("salary"), rs.getString("department"), rs.getString("position"),
						rs.getString("email_address"), rs.getLong("contact_number"), rs.getString("picture"));
			},filter.getDepartment());
			
				}
				else {
		 return null;
		 }
				
	
		}
	@Override
	public List<Employee> getAllEmployee(FilterAPI filter) {
		
		 
		return jdbc.query(Get_AllEmployee_Query, (rs, rowNum) -> {
			return new Employee(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"),
					rs.getLong("salary"), rs.getString("department"), rs.getString("position"),
					rs.getString("email_address"), rs.getLong("contact_number"),rs.getString("picture"));
		},new Object[]{filter.getPno()});

	}

	@Override
	public List<Employee> GetPage(int Page){
	System.out.println(Page);


	Page=Page*3;
 
	System.out.println("inside Get Page"+Page);
			return jdbc.query(Paging, (rs, rowNum) -> {
				return new Employee(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getLong("salary"), rs.getString("department"), rs.getString("position"),
						rs.getString("email_address"), rs.getLong("contact_number"), rs.getString("picture"));
			},Page);

	}


	
	
	
	
	 
	
	
	
	
	
	
	
	
	
//	@Override
//	public List<Employee> FilterEmployees(FilterAPI filter) {
// 
//		System.out.println("This is dept:" + filter.getDepartment());
//		System.out.println("This is Posit:"+ filter.getPosition());
//		String myFilter = "SELECT * FROM EMPLOYEE";
//		Boolean isFiltered = false;
//		
//		String postQuery = "";
//		
//		if(filter.getPosition()!= "")  
//		{
//			System.out.println("inside position");
//			if(isFiltered == false) {
//				myFilter += " WHERE ";
//			}
//			else {
//				myFilter += " AND ";
//			}
//			myFilter += "position = \"" + filter.getPosition() + "\"";
//			
//			isFiltered = true;
////			return jdbc.query(Filter_By_Position, (rs, rowNum) -> {
////				return new Employee(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"),
////						rs.getLong("salary"), rs.getString("department"), rs.getString("position"),
////						rs.getString("email_address"), rs.getLong("contact_number"), rs.getString("picture"));
////			},filter.getPosition());
//		}
//		if(filter.getDepartment()!= "") 
//		{
//			System.out.println("inside department");
//			
//			if(isFiltered == false) {
//				myFilter += " WHERE ";
//			}
//			else {
//				myFilter += " AND ";
//			}
//			myFilter += "department = \"" + filter.getDepartment() + "\"";
//			
//			isFiltered = true;
//			
////			return jdbc.query(Filter_By_Department, (rs, rowNum) -> {
////				return new Employee(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"),
////						rs.getLong("salary"), rs.getString("department"), rs.getString("position"),
////						rs.getString("email_address"), rs.getLong("contact_number"), rs.getString("picture"));
////			},filter.getDepartment());
////			
//		}else {
//			
//	System.out.println("Something is wrong with conditions");
//		 return null;
//		}
//		
//		if()
//		return jdbc.query(myFilter, (rs, rowNum) -> {
//			return new Employee(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"),
//					rs.getLong("salary"), rs.getString("department"), rs.getString("position"),
//					rs.getString("email_address"), rs.getLong("contact_number"), rs.getString("picture"));
//		});
//		
//	}	

}

