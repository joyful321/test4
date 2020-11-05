package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tools.databaseConnection;
import vo.User;
import dao.UserDao;

@WebServlet(urlPatterns = "/login.do")
public class LoginController extends HttpServlet {

	// 数据库连接类
	private databaseConnection dbc;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		// 连接数据库
		try {
			this.dbc = new databaseConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 1.取数据
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String vcode = request.getParameter("vcode");
		String checkBox = request.getParameter("autologin");

		HttpSession session = request.getSession();
		String saveVcode = (String) session.getAttribute("verifyCode");
		String forwardPath = "";

		if (!vcode.equalsIgnoreCase(saveVcode)) {// 验证码不正确
			request.setAttribute("info", "验证码不正确");
			forwardPath = "/error.jsp";
		} else {// 验证码正确
			UserDao userDao = new UserDao(this.dbc.getConnection());
			User user = userDao.get(userName);
			if (user == null) {// 用户名不存在
				request.setAttribute("info", "您输入的用户名不存在");
				forwardPath = "/error.jsp";

			} else {// 用户名存在
				if (!user.getPassword().equals(password)) {// 密码错误
					request.setAttribute("info", "密码不正确");
					forwardPath = "/error.jsp";
				} else {// 密码正确
					Cookie cookieUser = new Cookie("userName",user.getUserName());
					Cookie cookiePass = new Cookie("password",user.getPassword());
					if ("on".equals(checkBox)) {
						cookieUser.setMaxAge(60 * 60 * 24 * 7);
						cookiePass.setMaxAge(60 * 60 * 24 * 7);
					} else {// 销毁
						cookieUser.setMaxAge(0);
						cookiePass.setMaxAge(0);
					}
					response.addCookie(cookieUser);
					response.addCookie(cookiePass);

					session.setAttribute("currentUser", user);
					forwardPath = "/main.jsp";
				}
			}

		}
		// 3.转发
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);

	}

}
