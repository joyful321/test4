package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import vo.Page;
import vo.User;

public class UserDao {
	private Connection con;
	public UserDao(Connection con) {
		this.con=con;
	}
	public User get(String userName) {
		User user = null;
		try {
			// 3.�������
			String sql = "select * from t_user where userName=?";
			PreparedStatement pst = con.prepareStatement(sql);
			// 4.ִ�����
			pst.setString(1, userName);
			ResultSet rs = pst.executeQuery();
			// 5.��Ӧ����
			if (rs.next()) {
				user = new User(rs.getString("userName"), rs.getString("password"),
						rs.getString("chrName"));
			}
			// 6.�ر�����
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}
	
	public boolean insert(User user) {
		try {
			// 3.�������
			String sql = "INSERT INTO t_user VALUES(?, ?, ?, ?, ?, ?)";
			PreparedStatement pst = con.prepareStatement(sql);
			// 4.ִ�����
			pst.setString(1, user.getUserName());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getChrName());
			pst.setString(4, user.getMail());
			pst.setString(5, user.getProvinceCode());
			pst.setString(6, user.getCityCode());
//			System.out.println(user.toString());
//			ResultSet rs = pst.executeUpdate();
			// 5.��Ӧ����
			if (pst.executeUpdate()>0) {
				return true;
			}
			// 6.�ر�����
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean delete(String userNames){
		boolean flag=false;
		
		String[] array=userNames.split(",");
		String sql="delete from t_user where userName in(";
		StringBuffer sb=new StringBuffer("?");
		for(int i=0;i<array.length-1;i++)
			sb.append(",?");
		sql=sql+sb.toString()+")";
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(sql);
			for(int i=0;i<array.length;i++)
				pst.setString(i+1, array[i]);
			if (pst.executeUpdate()>0) {
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean update(User user){
		boolean flag=false;
		
		try {
			String sql = "update t_user set password=?,chrName = ?,mail=?,provinceCode=?,cityCode = ?";
			sql += " where userName =? ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, user.getPassword());
			pst.setString(2, user.getChrName());
			pst.setString(3, user.getMail());
			pst.setString(4, user.getProvinceCode());
			pst.setString(5, user.getCityCode());
			pst.setString(6, user.getUserName());
			if(pst.executeUpdate()>0){
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return flag;
	}
	public ArrayList<User> query(User user,Page page){
		ArrayList<User> list=new ArrayList<User>();
		StringBuffer condition=new StringBuffer();
		if(user.getUserName()!=null&&!"".equals(user.getUserName())){
			condition.append(" and userName like '%").append(user.getUserName()).append("%'");
		}
		if(user.getChrName()!=null&&!"".equals(user.getChrName())){
			condition.append(" and chrName like '%").append(user.getChrName()).append("%'");
		}
		if(user.getMail()!=null&&!"".equals(user.getMail())){
			condition.append(" and mail like '%").append(user.getMail()).append("%'");
		}
		if(user.getProvinceName()!=null&&!"".equals(user.getProvinceName())){
			condition.append(" and provinceName like '%").append(user.getProvinceName()).append("%'");
		}
		if(user.getCityName()!=null&&!"".equals(user.getCityName())){
			condition.append(" and cityName like '%").append(user.getCityName()).append("%'");
		}
		
		int begin=page.getPageSize()*(page.getPageNumber()-1);
		String sql="select userName,password,chrName,mail,A.provinceCode provinceCode,";
		sql+=" B.provinceName provinceName,A.cityCode cityCode,C.cityName cityName ";
		sql+=" from t_user A left join t_province B ";
		sql+=" on A.provinceCode=B.provinceCode left join t_city C on A.cityCode=C.cityCode ";
		sql+=" where 1=1 ";
//		sql+=condition+" order by "+page.getSort()+" "+page.getSortOrder()+" limit "+begin+","+page.getPageSize();
		sql+=condition+" limit "+begin+","+page.getPageSize();
		try {
			Statement pst = con.createStatement();
			ResultSet rs = pst.executeQuery(sql);
			while(rs.next()){
				User userResult=new User();
				userResult.setUserName(rs.getString("userName"));
				userResult.setPassword(rs.getString("password"));
				userResult.setChrName(rs.getString("chrName"));
				userResult.setMail(rs.getString("mail"));
				userResult.setProvinceCode(rs.getString("provinceCode"));
				userResult.setProvinceName(rs.getString("provinceName"));
				userResult.setCityCode(rs.getString("cityCode"));
				userResult.setCityName(rs.getString("cityName"));
				list.add(userResult);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	public int count(User user,Page page){
		ArrayList<User> list=new ArrayList<User>();
		StringBuffer condition=new StringBuffer();
		if(user.getUserName()!=null&&!"".equals(user.getUserName())){
			condition.append(" and userName like '%").append(user.getUserName()).append("%'");
		}
		if(user.getChrName()!=null&&!"".equals(user.getChrName())){
			condition.append(" and chrName like '%").append(user.getChrName()).append("%'");
		}
		if(user.getMail()!=null&&!"".equals(user.getMail())){
			condition.append(" and mail like '%").append(user.getMail()).append("%'");
		}
		if(user.getProvinceName()!=null&&!"".equals(user.getProvinceName())){
			condition.append(" and provinceName like '%").append(user.getProvinceName()).append("%'");
		}
		if(user.getCityName()!=null&&!"".equals(user.getCityName())){
			condition.append(" and cityName like '%").append(user.getCityName()).append("%'");
		}
		
		int begin=page.getPageSize()*(page.getPageNumber()-1);
		String sql="select userName,password,chrName,mail,A.provinceCode provinceCode,";
		sql+=" B.provinceName provinceName,A.cityCode cityCode,C.cityName cityName ";
		sql+=" from t_user A left join t_province B ";
		sql+=" on A.provinceCode=B.provinceCode left join t_city C on A.cityCode=C.cityCode ";
		sql+=" where 1=1 ";
//		sql+=condition+" order by "+page.getSort()+" "+page.getSortOrder()+" limit "+begin+","+page.getPageSize();
		sql+=condition;
		try {
			Statement pst = con.createStatement();
			ResultSet rs = pst.executeQuery(sql);
			while(rs.next()){
				User userResult=new User();
				userResult.setUserName(rs.getString("userName"));
				userResult.setPassword(rs.getString("password"));
				userResult.setChrName(rs.getString("chrName"));
				userResult.setMail(rs.getString("mail"));
				userResult.setProvinceCode(rs.getString("provinceCode"));
				userResult.setProvinceName(rs.getString("provinceName"));
				userResult.setCityCode(rs.getString("cityCode"));
				userResult.setCityName(rs.getString("cityName"));
				list.add(userResult);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int sum;
		sum=list.size();
		return sum;
	}

}
