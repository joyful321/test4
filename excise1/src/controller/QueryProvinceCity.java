package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import tools.databaseConnection;
import vo.City;
import vo.Province;
import dao.ProvinceCityDao;

@WebServlet(urlPatterns = "/queryProvinceCity.do")
public class QueryProvinceCity extends HttpServlet {

	// ��ݿ�������
	private databaseConnection dbc;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		System.out.println("%%%%%%%%%%%%%%%%%%%%%%");
		response.setContentType("text/html;charset=utf-8");
		// ������ݿ�
		try {
			this.dbc = new databaseConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String provinceCode = request.getParameter("provinceCode");
		String jsonStr = "";
		ProvinceCityDao dao = new ProvinceCityDao(this.dbc.getConnection());
		if (provinceCode == null) {
			ArrayList<Province> list = dao.queryProvince();
			jsonStr = new Gson().toJson(list);
		} else {
			ArrayList<City> list = dao.queryCity(provinceCode);
			jsonStr = new Gson().toJson(list);
		}
		PrintWriter out = response.getWriter();
//		System.out.println(jsonStr);
		out.print(jsonStr);
		out.flush();
		out.close();
	}

}
