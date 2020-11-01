package dao;

import java.util.HashMap;

import vo.Page;

public class PageDao {
//	public Page getPageParams(HashMap<String, Object> mapPage){
//		Page page=new Page();
////		this.pageSize = Integer.parseInt((String) mapPage.get(pageSize));
////		this.pageNumber = Integer.parseInt((String) mapPage.get(pageNumber));
////		this.sort = (String) mapPage.get(pageNumber);
////		this.sortOrder = (String) mapPage.get(sortOrder);
//		page.setPageSize(Integer.parseInt((String) mapPage.get("pageSize")));
//		page.setPageNumber(Integer.parseInt((String) mapPage.get("pageNumber")));
//		page.setSort((String) mapPage.get("sort"));
//		page.setSortOrder((String) mapPage.get("sortOrder"));
//		return page;
//	}

	public Page getPageParams(HashMap<String, Object> mapPage) {
		// TODO Auto-generated method stub
		Page page=new Page();
		page.setPageSize(Integer.parseInt((String) mapPage.get("pageSize")));
		page.setPageNumber(Integer.parseInt((String) mapPage.get("pageNumber")));
		page.setSort((String) mapPage.get("sort"));
		page.setSortOrder((String) mapPage.get("sortOrder"));
		return page;
	}
}
