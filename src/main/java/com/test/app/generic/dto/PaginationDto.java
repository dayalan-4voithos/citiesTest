package com.test.app.generic.dto;

import java.io.Serializable;
import java.util.Optional;

public class PaginationDto implements Serializable {

	private static final long serialVersionUID = 1009L;

	private Integer pageNumber;
	private Integer pageSize;
	private Optional<Integer> totalPages;

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Optional<Integer> getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = Optional.of(totalPages);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "{\"pageNumber\":\"" + pageNumber + "\", pageSize\":\"" + pageSize + "\", totalPages\":\"" + totalPages
				+ "}";
	}

}
