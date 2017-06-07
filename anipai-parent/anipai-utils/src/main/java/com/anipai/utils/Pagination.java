package com.anipai.utils;

public class Pagination {
	private String sort;
	private String order;
	private int offset;
	private int limit;
	/*private Long agencyId;*/
	
	public Pagination() {}
	public Pagination(String sort, String order, int offset, int limit/*, Long agencyId*/) {
		this.sort = formatToUnderScoreCase(sort);
		this.order = order;
		this.offset = offset;
		this.limit = limit;
		/*this.agencyId =agencyId;*/
	}
	
	public String getSort() {
		return sort;
	}
	public Pagination setSort(String sort) {
		this.sort = Pagination.formatToUnderScoreCase(sort);
		return this;
	}
	public String getOrder() {
		return order;
	}
	public Pagination setOrder(String order) {
		this.order = order;
		return this;
	}
	public int getOffset() {
		return offset;
	}
	public Pagination setOffset(int offset) {
		this.offset = offset;
		return this;
	}
	public int getLimit() {
		return limit;
	}
	public Pagination setLimit(int limit) {
		this.limit = limit;
		return this;
	}
	/*public Long getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(Long agencyId) {
		this.agencyId = agencyId;
	}*/
	
	public static String formatToUnderScoreCase(String camelCase) {
		StringBuilder underScoreCase = new StringBuilder();
		if(camelCase != null && camelCase.length() > 0) {
			underScoreCase.append(camelCase.substring(0, 1).toLowerCase());
		}
		for(int i = 1; i < camelCase.length(); i++) {
			String s = camelCase.substring(i, i + 1);
			if(s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
				underScoreCase.append("_");
			}
			underScoreCase.append(s.toLowerCase());
		}
		return underScoreCase.toString();
	}
}
