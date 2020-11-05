package vo;

public class User {
	private String userName;
	private String password;
	private String chrName;
	private String mail;
	private String provinceCode;
	private String provinceName;
	private String cityCode;
	private String cityName;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	 public User(String userName, String password, String chrName, String mail,
			String provinceCode,String cityCode) {
		super();
		this.userName = userName;
		this.password = password;
		this.chrName = chrName;
		this.mail = mail;
		this.provinceCode = provinceCode;
//		this.provinceName = provinceName;
		this.cityCode = cityCode;
//		this.cityName = cityName;
	}



	public User(String userName, String password, String chrName) {
		 super();
		 this.userName = userName;
		 this.password = password;
		 this.chrName = chrName;
	 }
	// public String getUserName() {
	// return userName;
	// }
	// public void setUserName(String userName) {
	// this.userName = userName;
	// }
	// public String getPassword() {
	// return password;
	// }
	// public void setPassword(String password) {
	// this.password = password;
	// }
	// public String getChrName() {
	// return chrName;
	// }
	// public void setChrName(String chrName) {
	// this.chrName = chrName;
	// }
	// @Override
	// public String toString() {
	// return "User [userName=" + userName + ", password=" + password
	// + ", chrName=" + chrName + "]";
	// }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getChrName() {
		return chrName;
	}

	public void setChrName(String chrName) {
		this.chrName = chrName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}



	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password
				+ ", chrName=" + chrName + ", mail=" + mail + ", provinceCode="
				+ provinceCode + ", provinceName=" + provinceName
				+ ", cityCode=" + cityCode + ", cityName=" + cityName + "]";
	}



	


}
