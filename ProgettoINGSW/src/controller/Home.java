package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Home
{
	/*@RequestMapping("/add")
	public String add(@RequestParam("t1") int a)
	{
		return "second.jsp";
	}*/

	//@RequestMapping(value = "index", method = RequestMethod.POST)
	@PostMapping(path = "/register")
	public String registerUser(ModelMap model, @RequestParam("name") String name)
	{
		System.out.println("L'utente si chiama " + name);
		return "second";
	}
}
