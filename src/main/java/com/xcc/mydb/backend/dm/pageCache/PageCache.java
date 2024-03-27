package com.xcc.mydb.backend.dm.pageCache;

import com.xcc.mydb.backend.dm.page.Page;

/**
 * 页面缓存接口
 */
public interface PageCache {
    public static final int PAGE_SIZE = 1 << 13;
    int newPage(byte[] initData);
    Page getPage(int pgno) throws Exception;
    void close();
    void release(Page page);

    void truncateByBgno(int maxPgno);
    int getPageNumber();
    void flushPage(Page page);


}
