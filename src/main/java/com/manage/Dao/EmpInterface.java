package com.manage.Dao;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.manage.Entity.Employee;
import com.manage.Entity.FilterAPI;
 
public  interface EmpInterface {
	Employee getEmployeebyid(long id);
	List<Employee> getAllEmployee(long id);  
	Employee saveEmployee(Employee employee,MultipartFile photo) throws IOException;
	Employee updatEmployee(Employee employee,MultipartFile photo) throws IOException;
	long deleteEmployee(long id);
	List<Employee> FilterEmployees(FilterAPI filter);
	List<Employee> getAllEmployee(FilterAPI filter);
	List<Employee> GetPage(int Page);
	
}