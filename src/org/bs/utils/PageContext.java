package org.bs.utils;

import org.bs.model.Page;

public class PageContext {
	private static ThreadLocal pageThreadLocal = new ThreadLocal();

	public static Page getPage() {
		if (pageThreadLocal.get() == null) {
			Page page = new Page();
			page.setPageNo(1);
			page.setPageSize(10);
			PageContext.pageThreadLocal.set(page);
		}
		return (Page) pageThreadLocal.get();
	}

	public static void remove() {
		PageContext.pageThreadLocal.remove();
	}
}
