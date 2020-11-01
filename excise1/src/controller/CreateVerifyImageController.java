package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CreateImage;

@WebServlet(urlPatterns="/createVerifyImage.do")
public class CreateVerifyImageController extends HttpServlet {

	
	 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CreateImage createImage=new CreateImage();
		String vcode=createImage.createCode();
		HttpSession session=request.getSession();
		session.setAttribute("verifyCode", vcode);
		
		response.setContentType("img/jpeg");
		response.setHeader("Prama", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		//Ê§Ð§Ê±¼ä
		response.setDateHeader("Expires",0);
		
		BufferedImage image=createImage.CreateImage(vcode);
		ServletOutputStream out=response.getOutputStream();
		ImageIO.write(image,"JPEG",out);
		out.flush();
		out.close();
		
	}

	
	

}
