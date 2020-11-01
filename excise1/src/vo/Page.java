package vo;

import java.util.HashMap;

public class Page {
	private int pageSize;
	private int pageNumber; 
	private String sort;
	private String sortOrder;
//	private HashMap<String,Object> mapPage;
	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Page(int pageSize, int pageNumber, String sort, String sortOrder) {
		super();
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
		this.sort = sort;
		this.sortOrder = sortOrder;
	}
//	public  Page getPageParams(HashMap<String,Object> mapPage){
//		Page page=new Page();
////		this.pageSize = Integer.parseInt((String) mapPage.get(pageSize));
////		this.pageNumber = Integer.parseInt((String) mapPage.get(pageNumber));
////		this.sort = (String) mapPage.get(pageNumber);
////		this.sortOrder = (String) mapPage.get(sortOrder);
//		page.setPageSize(Integer.parseInt((String) mapPage.get(pageSize)));
//		page.setPageNumber(Integer.parseInt((String) mapPage.get(pageNumber)));
//		page.setSort((String) mapPage.get(pageNumber));
//		page.setSortOrder((String) mapPage.get(sortOrder));
//		return page;
//	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	@Override
	public String toString() {
		return "Page [pageSize=" + pageSize + ", pageNumber=" + pageNumber
				+ ", sort=" + sort + ", sortOrder=" + sortOrder + "]";
	}
	
}
