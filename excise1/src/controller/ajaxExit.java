package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.UserDao;
import tools.databaseConnection;
import vo.User;

@WebServlet(urlPatterns = "/ajaxExit.do")
public class ajaxExit extends HttpServlet {
	// 数据库连接类
	private databaseConnection dbc;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		// 连接数据库
		try {
			this.dbc = new databaseConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 1.获取参数值
		String userName = request.getParameter("userName");
		// 2.获取HttpSession对象
		HttpSession session = request.getSession();

		// Map存放放回的信息
		Map<String, Object> map = new HashMap<String, Object>();

		UserDao userDao = new UserDao(this.dbc.getConnection());

		User user = userDao.get(userName);
		if (user == null) {
			map.put("code", 0);
			map.put("info", "");
			// System.out.println("**&&&&&&&&&*");
		} else {// 用户名存在
			map.put("code", 1);
			map.put("info", "用户名存在，请重新输入");
		}
		String jsonStr = new Gson().toJson(map);
		// 字符流输出字符串
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
	}

}
