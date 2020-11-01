package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutController extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		
		//Ïú»Ùcookie
		Cookie cookieUser=new Cookie("userName","");
		Cookie cookiePass=new Cookie("password","");
		cookieUser.setMaxAge(0);
		cookiePass.setMaxAge(0);
        response.addCookie(cookieUser);
        response.addCookie(cookiePass);
        
        //Ìø×ª
        request.getRequestDispatcher("login.jsp").forward(request,response);
	}


}
