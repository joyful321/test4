package filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.ResourceDao;
import tools.databaseConnection;
import vo.User;

public class PermissionFilter implements Filter {

	private String notCheckPath;//不需过滤的请求地址，从web.xml中获取
	//数据库连接类
	private databaseConnection dbc;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//连接数据库
		try {
			this.dbc=new databaseConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		HttpServletRequest request=(HttpServletRequest) req;
		HttpSession session=request.getSession();
		String path=request.getServletPath();
		
		User currentUser=(User) session.getAttribute("currentUser");
		if(currentUser==null){ //说明没登陆
			if(notCheckPath.indexOf(path) != -1)//说明要访问的页面是放行的
				chain.doFilter(request,resp);//放行
			else{
				request.setAttribute("info", "请先登陆！");
				request.setAttribute("path", "login.html");
				request.getRequestDispatcher("/error.jsp").forward(request, resp);
			}
		}else{//登陆了
//			chain.doFilter(request,resp);
			//实例化真实主题类
			ResourceDao dao=new ResourceDao(this.dbc.getConnection());
			List<String> list=dao.getUrlByUserName(currentUser.getUserName());
			if(list.contains(path)){
				chain.doFilter(request,resp);
			}else{
				request.setAttribute("info", "您是普通用户，没有权限访问");
				request.setAttribute("path", "login.html");
				request.getRequestDispatcher("/block.jsp").forward(request, resp);
			}
		}
//		if(notCheckPath.indexOf(path)==-1){
//			
//			if(session.getAttribute("currentUser")==null){//没有登陆
//				request.setAttribute("info","没有权限访问");
//				request.getRequestDispatcher("/error.jsp").forward(request,resp);
//				
//			}else{//已登录
//				chain.doFilter(req,resp);
//			}
//		}else{//不需要过滤
//			chain.doFilter(req,resp);
//		}
//		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		notCheckPath=config.getInitParameter("notCheckPath");
	}

}
