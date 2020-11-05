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

import com.google.gson.Gson;

import tools.databaseConnection;
import dao.UserDao;

@WebServlet(urlPatterns = "/deleteUser.do")
public class DeleteUser extends HttpServlet {
	// 数据库连接类
	private databaseConnection dbc;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 连接数据库
		try {
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			this.dbc = new databaseConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String userName = request.getParameter("userName");
		UserDao dao = new UserDao(this.dbc.getConnection());
		boolean flag=dao.delete(userName);
		Map<String,Object> map=new HashMap<String,Object>();
		if(flag){
			map.put("code", 0);
			map.put("info", "删除成功！");
		}else{
			map.put("code", 1);
			map.put("info", "删除失败！");
		}
		
		String jsonStr=new Gson().toJson(map);
		System.out.println(jsonStr + "^^^^^^^^^^^^^^^^^^^^");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
	}

}
