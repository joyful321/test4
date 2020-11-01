package vo;

public class recourse {
	private int resourceId;
    private String resourceName;
    private String url;

	public recourse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public recourse(int resourceId, String resourceName, String url) {
		super();
		this.resourceId = resourceId;
		this.resourceName = resourceName;
		this.url = url;
	}
	public int getResourceId() {
		return resourceId;
	}
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "recourse [resourceId=" + resourceId + ", resourceName="
				+ resourceName + ", url=" + url + "]";
	}
}
