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

import tools.databaseConnection;
import vo.User;

import com.google.gson.Gson;

import dao.UserDao;

@WebServlet(urlPatterns = "/ajaxRegisterCheck.do")
public class ajaxRegisterCheck extends HttpServlet {

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

		// 1.按照表单的各元素的name属性值获取各请求参数值
				String action=request.getParameter("action");
				String userName = request.getParameter("userName");
				String password = request.getParameter("password");
				String name = request.getParameter("name");
				String Email = request.getParameter("E-mail");
				String province = request.getParameter("provinceCode");
				String city = request.getParameter("cityCode");

//		System.out.println(userName);
		// 2.获取HttpSession对象
		HttpSession session = request.getSession();

		// Map存放放回的信息
		Map<String, Object> map = new HashMap<String, Object>();

		UserDao userDao = new UserDao(this.dbc.getConnection());
		
//			String name = request.getParameter("name");
//			String Email=request.getParameter("Email");
//			String province=request.getParameter("province");
//			String city=request.getParameter("city");

			
			User user=new User(userName,password,name,Email,province,city);
			System.out.println(user.toString()+"$$$$$$$$");
			boolean success = false ;
			String info="";
			if(action.equals("")){
				success = userDao.insert(user);
				info="注册";
			}
			else if(action.equals("insert")){
				success = userDao.insert(user);
				info="新增";
			}
			else if(action.equals("update")){
				success = userDao.update(user);
				info="修改";
			}
			// 存放返回信息的Map
			if (success) {
				map.put("code", 0);
				map.put("info", info+"成功!");
			} else {
				map.put("code", 1);
				map.put("info", info+"失败!");
			}


			
//			if(userDao.insert(user)==0){
//				map.put("register", 0);
//				System.out.println(userDao.insert(user)+"******");
//			}
//				
//			else {
//				map.put("register", 1);
//				System.out.println(userDao.insert(user)+"&&&&&");
//			}
		
		
		String jsonStr = new Gson().toJson(map);
		// 字符流输出字符串
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();

	}
}
