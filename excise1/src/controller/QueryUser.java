package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.databaseConnection;
import vo.Page;
import vo.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.PageDao;
import dao.UserDao;

@WebServlet(urlPatterns = "/queryUser.do")
public class QueryUser extends HttpServlet {

	// 数据库连接类
	private databaseConnection dbc;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 连接数据库
		try {
			this.dbc = new databaseConnection();
//			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String queryParams = request.getParameter("queryParams");
		String pageParams = request.getParameter("pageParams");
//		System.out.println("查询参数：" + pageParams + "&&&&&&&&&&&&&&&&&&&");
//		System.out.println("分页参数：" + queryParams + "&&&&&&&&&&&&&&&&&&&");
		// 将json字符串转为java对象
		Gson gson = new GsonBuilder().serializeNulls().create();
		HashMap<String, Object> mapPage = gson.fromJson(pageParams,
				HashMap.class);
		PageDao pageDao = new PageDao();
		Page page = ((PageDao) pageDao).getPageParams(mapPage);
		User user = new User();
		if (queryParams != null) {
			user = gson.fromJson(queryParams, User.class);
		}
		UserDao dao = new UserDao(this.dbc.getConnection());
		ArrayList<User> rows = dao.query(user, page);
		int total = dao.count(user, page);

		HashMap<String, Object> mapReturn = new HashMap<String, Object>();
		mapReturn.put("rows", rows);
		mapReturn.put("total", total);
		String jsonStr = gson.toJson(mapReturn);
		System.out.println(jsonStr + "^^^^^^^^^^^^^^^^^^^^");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();

	}

}
