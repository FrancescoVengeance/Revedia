package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import database.DatabaseManager;
import jdbcModels.UserJDBC;
import model.User;

@Controller
public class Login
{

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void checkLogin(HttpSession session, HttpServletResponse response) throws IOException
	{

		String nickName = (String) session.getAttribute("nickname");

		if (nickName != null)
		{
			System.out.println(nickName);
			response.getWriter().println("<html>");
			response.getWriter().println("<body>");
			response.getWriter().println("<h1>Sei loggato come '" + nickName + "'</h1>");
			response.getWriter().println("</body>");
			response.getWriter().println("</html>");
		}

	}

	@RequestMapping(value = "/tag del form register", method = RequestMethod.POST)
	public void loginForm(@RequestParam("/tag nick") String nick, @RequestParam("/tag pwd") String pwd,
			HttpSession session, HttpServletResponse response) throws SQLException, IOException
	{
		UserJDBC userJDBC = DatabaseManager.getIstance().getDaoFactory().getUserJDBC();
		User user = userJDBC.getUser(nick);

		Cookie ck = new Cookie("nickname", nick);
		ck.setMaxAge(-1);

		if (userJDBC.validateLogin(pwd, nick))
		{

			response.addCookie(ck);
			response.getWriter().println("<script>window.location.href='home.jsp';</script>");

		}

	}

}
