package com.anipai.customer.utils;

import java.util.List;

public class PageModel<T> {
	private int total;
	private List<T> rows;
	public int getTotal() {
		return total;
	}
	public PageModel<T> setTotal(int total) {
		this.total = total;
		return this;
	}
	public List<T> getRows() {
		return rows;
	}
	public PageModel<T> setRows(List<T> rows) {
		this.rows = rows;
		return this;
	}
}
