package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.Download;
import dao.DownloadDao;

@WebServlet(urlPatterns="/getDownloadList.do")
public class GetDownloadListController extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");


		HttpSession session = request.getSession();
		DownloadDao downloadDao=new DownloadDao();
		List<Download> downloadList=downloadDao.get();
		
		session.setAttribute("downloadList", downloadList);

		// 3.转发
		RequestDispatcher rd = request.getRequestDispatcher("/download.jsp");
		rd.forward(request, response);
	}

}
