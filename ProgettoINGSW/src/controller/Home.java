package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Home
{
	@RequestMapping("/add")
	public String add(@RequestParam("t1") int a)
	{
		return "second.jsp";
	}
}
