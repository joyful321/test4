package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DownloadDao;
import vo.Download;

@WebServlet(urlPatterns="/download.do")
public class DownloadController extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id=request.getParameter("下载");
		
		Download download=new Download();
		DownloadDao downloadDao=new DownloadDao();
		download=downloadDao.findById(Integer.parseInt(id));
		
		String pa=download.getPath();

		//1.获取要下载的文件的绝对路径
		String path=request.getServletContext().getRealPath(pa);
		//2.获取要下载的文件名
		String fileName=path.substring(path.lastIndexOf("//"+1));
		//3.设置 context-disposition响应头，控制浏览器以下载的形式打开
		response.setHeader("context-disposition", "attachment;filename="+URLEncoder.encode(fileName, "UTF-8"));
		//4.获取下载文件的输入流
		InputStream in=new FileInputStream(path);
		int len=0;
		//5.创建数据缓冲区
		byte[] buffer=new byte[1024];
		//6.获取OutputStream流
		ServletOutputStream out=response.getOutputStream();
		//7.讲FileInputStream流写入到buffer缓冲区
		while((len=in.read(buffer))!=-1){
			//8.使用OutputStream将缓冲区的数据输出到客户浏览器
			out.write(buffer,0,len);
		}
		in.close();
		out.close();
		
	
	}

}
