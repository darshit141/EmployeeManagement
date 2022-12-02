package com.manage.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.manage.Dao.EmpInterface;

@Controller
public class PaginationAPI_Controller {

	@Autowired
	 private EmpInterface Emp;

	@GetMapping("/Records")
 	public String GetPage(@RequestParam(required = false)  String Page,Model model)
 	{
	

	int Pages= Integer.parseInt(Page);
 		model.addAttribute("Employee",Emp.GetPage(Pages));
 		
 		
 		
		return "index";
 	}    	
}
