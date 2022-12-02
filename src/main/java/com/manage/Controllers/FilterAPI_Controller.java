package com.manage.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.manage.Dao.EmpInterface;
import com.manage.Entity.FilterAPI;

@Controller
public class FilterAPI_Controller {

	@Autowired
	 private EmpInterface Emp;

	
	@GetMapping("/pages")
	public String getpage(@ModelAttribute("FilterAPI")@RequestBody FilterAPI page, Model model){
		
		long pageno= page.getPno();
		model.addAttribute("Employee", Emp.getAllEmployee(pageno));
		return "index";
	}	
	
	
	@PostMapping("/filterEmployee")
	public String FilterEmployees(@ModelAttribute("FilterAPI")@RequestBody FilterAPI filter,Model model){
		
		 model.addAttribute("Employee",Emp.FilterEmployees(filter));
		 
		 return "index";	
		
		
		
		}
}
