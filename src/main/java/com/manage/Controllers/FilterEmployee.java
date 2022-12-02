
package com.manage.Controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.manage.Dao.EmpInterface;
import com.manage.Entity.Employee;

@Controller
public class FilterEmployee {

	@Autowired
	private EmpInterface Emp;

	@GetMapping("/")
	public String viewHomepage(Model model) {

		model.addAttribute("Employee", Emp.getAllEmployee(0));
		return "index";
	}

	@GetMapping("/new_Employee")
	public String addEmployee(Model model) {
		Employee employee = new Employee();
		model.addAttribute("Employee", employee);
		return "new_Employee";
	}

	@PostMapping("/saveEmployee")
	public String addEmployee(@ModelAttribute("Employee") @RequestBody Employee employee,
			@RequestParam("file") MultipartFile file) throws IOException {

		if (file.isEmpty()) {
			System.out.println("File is empty bro");

		} else {
			employee.setPicture(file.getOriginalFilename());
			System.out.println(employee.getPicture() + "::::::::::This is image name");
			String path = new File(".").getCanonicalPath() + "/src/main/resources/static/images";
			Path copyLocation = Paths.get(path + File.separator + file.getOriginalFilename());
			System.out.println(copyLocation);

			System.out.println(path + ":::::::::This is image path new one");
			Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

		}
		Emp.saveEmployee(employee, file);

		return "redirect:/";

	}

	@PostMapping("/updateEmployee")
	public String updatEmployee(@ModelAttribute("Employee") @RequestBody Employee employee, MultipartFile file)
			throws IOException {

		if (file.isEmpty()) {

			System.out.println("File is empty bro");
			Emp.updatEmployee(employee, file);
		} else {
			employee.setPicture(file.getOriginalFilename());
			String path = new File(".").getCanonicalPath() + "/src/main/resources/static/images";
			Path copyLocation = Paths.get(path + File.separator + file.getOriginalFilename() + employee.getId());

			Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
		}
		Emp.updatEmployee(employee, file);
		return "redirect:/";

	}

	@GetMapping("/update_employee/{id}")
	public String getEmployeebyid(@PathVariable(value = "id") int id, Model model, MultipartFile file) {

		System.out.println(Emp.getEmployeebyid(id));

		model.addAttribute("Employee", Emp.getEmployeebyid(id));
		return "update_employee";
	}

	@GetMapping("/DeleteEmployee/{id}")
	public String deleteEmployee(@PathVariable(value = "id") int id, Model model) {
		Emp.deleteEmployee(id);
		return "redirect:/";
	}

}
