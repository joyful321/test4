package vo;

public class City {
	private String provinceCode;
	private String cityName;
	public City() {
		super();
		// TODO Auto-generated constructor stub
	}
	public City(String provinceCode, String cityName) {
		super();
		this.provinceCode = provinceCode;
		this.cityName = cityName;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	@Override
	public String toString() {
		return "City [provinceCode=" + provinceCode + ", cityName=" + cityName + "]";
	}
}
