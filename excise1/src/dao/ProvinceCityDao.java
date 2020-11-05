package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.City;
import vo.Province;
import vo.User;



public class ProvinceCityDao {
	private Connection con;
	public ProvinceCityDao(Connection con) {
		this.con=con;
	}
	public ArrayList<Province> queryProvince(){
		ArrayList<Province> list = new ArrayList() ;
		try {
			// 3.�������
			String sql = "select * from t_province ";
			PreparedStatement pst = con.prepareStatement(sql);
			// 4.ִ�����
			ResultSet rs = pst.executeQuery();
			// 5.��Ӧ����
			while (rs.next()) {
				Province pro = new Province(rs.getString("provinceCode"), rs.getString("provinceName"));
				System.out.println(pro.toString());
				list.add(pro);
			}
			// 6.�ر�����
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	public ArrayList<City> queryCity(String provinceCode){
		ArrayList<City> list = new ArrayList() ;
		try {
			// 3.�������
			String sql = "select * from t_city where provinceCode=?";
			PreparedStatement pst = con.prepareStatement(sql);
			// 4.ִ�����
			pst.setString(1, provinceCode);
			ResultSet rs = pst.executeQuery();
			// 5.��Ӧ����
			while (rs.next()) {
				City city = new City(rs.getString("provinceCode"), rs.getString("cityName"), rs.getString("cityCode"));
				list.add(city);
			}
			// 6.�ر�����
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
