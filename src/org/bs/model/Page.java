package org.bs.model;

public class Page {
	private int pageNo;
	private int pageSize;
	private int pageIndex;
	private int recordsCount;
	private int pagesCount;
	private int begin;
	private int end;

	public int getBegin() {
		begin = pageIndex + 1;
		return begin;
	}

	public int getEnd() {
		end = pageIndex + pageSize;
		return end;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		this.pageIndex = (pageNo - 1) * pageSize;
		return pageIndex;
	}

	public void setPageIndex() {
		this.pageIndex = (pageNo - 1) * pageSize;
	}

	public int getRecordsCount() {
		return recordsCount;
	}

	public void setRecordsCount(int recordsCount) {
		if (recordsCount != 0) {
			this.pagesCount = recordsCount % pageSize == 0 ? recordsCount
					/ pageSize : recordsCount / pageSize + 1;
			if (this.pageNo > this.pagesCount) {
				this.pageNo = this.pagesCount;
			}
		} else {
			this.pagesCount = 0;
			this.pageNo = 1;
		}
		this.recordsCount = recordsCount;
	}

	public int getPagesCount() {
		return pagesCount;
	}

	public void setPagesCount() {
		this.pagesCount = recordsCount % pageSize == 0 ? recordsCount
				/ pageSize : recordsCount / pageSize + 1;
	}

	@Override
	public String toString() {
		return "Page [pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", pageIndex=" + pageIndex + ", recordsCount=" + recordsCount
				+ ", pagesCount=" + pagesCount + ", begin=" + begin + ", end="
				+ end + "]";
	}
}
