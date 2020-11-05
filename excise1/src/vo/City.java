package vo;

public class City {
	private String provinceCode;
	private String cityName;
	private String cityCode;
	public City() {
		super();
		// TODO Auto-generated constructor stub
	}
	public City(String provinceCode, String cityName, String cityCode) {
		super();
		this.provinceCode = provinceCode;
		this.cityName = cityName;
		this.cityCode = cityCode;
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
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	@Override
	public String toString() {
		return "City [provinceCode=" + provinceCode + ", cityName=" + cityName
				+ ", cityCode=" + cityCode + "]";
	}
	
}
