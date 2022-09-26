package com.test.app.generic.dto;

import java.io.Serializable;

public class PaginatedResponse implements Serializable {

	private static final long serialVersionUID = 1009L;

	Object results;
	PaginationDto pagination;

	public Object getResults() {
		return results;
	}

	public void setResults(Object results) {
		this.results = results;
	}

	public PaginationDto getPagination() {
		return pagination;
	}

	public void setPagination(PaginationDto pagination) {
		this.pagination = pagination;
	}

	@Override
	public String toString() {
		return "{\"results\":\"" + results + "\", pagination\":\"" + pagination + "}";
	}

}
