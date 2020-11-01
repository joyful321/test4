package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import tools.databaseConnection;
import vo.User;
import dao.UserDao;

@WebFilter(filterName="AutoLoginFilter")
public class AutoLoginFilter implements Filter {
	//数据库连接类
	private databaseConnection dbc;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		//连接数据库
				try {
					this.dbc=new databaseConnection();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest)req;
		HttpSession session=request.getSession();
		Cookie[] cookies=request.getCookies();
		
		boolean userT=false;
		boolean passT=false;
		String cookieUser="";
		String cookiePass="";
		
		if(cookies!=null){
			for(Cookie c:cookies){
				if("userName".equals(c.getName())){
					cookieUser=c.getValue();
					userT=true;
				}
				if("password".equals(c.getName())){
					cookiePass=c.getValue();
					passT=true;
				}
			}
		}
		if(userT&&passT){
			UserDao userD=new UserDao(this.dbc.getConnection());
			User user=userD.get(cookieUser);
			if(cookiePass.equals(user.getPassword())){
				session.setAttribute("currentUserName",user.getUserName());
                request.getRequestDispatcher("main.jsp").forward(req,resp);
			}
		}else{
			chain.doFilter(req,resp);
		}
		chain.doFilter(req,resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
