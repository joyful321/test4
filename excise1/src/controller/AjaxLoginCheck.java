package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import tools.databaseConnection;
import vo.User;
import dao.UserDao;

@WebServlet(urlPatterns = "/ajaxLoginCheck.do")
public class AjaxLoginCheck extends HttpServlet {

	// ��ݿ�������
	private databaseConnection dbc;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// ������ݿ�
		try {
			this.dbc = new databaseConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 1.��ȡ����ֵ
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String vcode = request.getParameter("vcode");
		String autologin = request.getParameter("autologin");
		 System.out.println(userName+password+vcode);
		// 2.��ȡHttpSession����
		HttpSession session = request.getSession();
		// ȡ��CreateVerifyImageController�д�ŵ���֤���ַ�
		String saveVcode = (String) session.getAttribute("verifyCode");
		// Map��ŷŻص���Ϣ
		Map<String, Object> map = new HashMap<String, Object>();

		boolean tag1 = false;
		boolean tag2 = false;
		boolean tag3 = false;
		// �ж��û����Ƿ�����
		if (userName == null || "".equals(userName)) {
			map.put("user", "0");
			map.put("userinfo", " 用户名不能为空");
		} else {
			tag1 = true;
			map.put("user", "1");
		}
		// �ж������Ƿ�����
		if (password == null || "".equals(password)) {
			map.put("password", "0");
			map.put("passwordinfo", " 密码不能为空");
		} else {
			tag2 = true;
			map.put("password", "1");
		}
		// �ж���֤���Ƿ�����
		if (vcode == null || "".equals(vcode)) {
			map.put("vcode", "0");
			map.put("vcodeinfo", " 验证码不能为空");
		} else {
			tag3 = true;
			map.put("vcode", "1");
		}
		if (tag1 && tag2 && tag3) {
			// �Ƚ���֤�������Ƿ���ȷ
			if (!saveVcode.equalsIgnoreCase(vcode)) {// ����ȷ
				System.out.println(saveVcode);
				System.out.println(vcode);
				map.put("code", 1);
				map.put("info", "验证码不正确");
			} else {// ��ȷ
				UserDao userDao = new UserDao(this.dbc.getConnection());
				User user = userDao.get(userName);
				if (user == null) {
					map.put("code", 2);
					map.put("info", "用户名不存在");
				} else {// �û������
					if (!user.getPassword().equals(password)) {
						map.put("code", 3);
						map.put("info", "密码不正确");
					} else {// �û���������ȷ
						session.setAttribute("currentUser", user);
						if (autologin != null) {
							// ����cookie
							Cookie cookieUser = new Cookie("userName", userName);
							Cookie cookiePass = new Cookie("password", password);
							cookieUser.setMaxAge(7 * 24 * 60 * 60);
							cookiePass.setMaxAge(7 * 24 * 60 * 60);
							response.addCookie(cookieUser);
							response.addCookie(cookiePass);
						}
						map.put("code", 0);
						map.put("info", "");
					}
				}
			}
		}else{
			map.put("code", 4);
			map.put("info", "");
		}
		String jsonStr = new Gson().toJson(map);
		// �ַ�������ַ�
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();

	}

}
