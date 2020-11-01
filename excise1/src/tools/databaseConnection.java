package tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class databaseConnection  {
	//数据库参数配置文件名
	private static final String JDBCPROPERTY="jdbc.properties";
	//准备数据库的四大参数
	private static String DBDRIVER="";
	private static String DBURL="";
	private static String DBUSER="";
	private static String PASSWORD="";
	
	//准备数据库连接对象
	private Connection conn;
	
	static {
		Properties property=new Properties();
		InputStream is = databaseConnection.class.getClassLoader().getResourceAsStream("resource/"+JDBCPROPERTY);
		try {
			//加载资源
			property.load(new InputStreamReader(is, "utf-8"));
			is.close();
			DBDRIVER=property.getProperty("DBDRIVER");
			DBURL=property.getProperty("DBURL");
			DBUSER=property.getProperty("DBUSER");
			PASSWORD=property.getProperty("PASSWORD");
			
			//加载驱动
			Class.forName(DBDRIVER);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//构造方法，实例化对象时创建连接对象
	public databaseConnection() throws Exception {
		this.conn=DriverManager.getConnection(DBURL,DBUSER,PASSWORD);
		
	}
	
	//直接返回实例化对象时创建的连接对象
	public  Connection getConnection() {
		return this.conn;
	}
	
	//关闭连接对象
	public void close() throws Exception {
		if(conn!=null) {
			this.conn.close();
		}
	}
	
	
}
