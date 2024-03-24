package com.xcc.mydb.backend.tm;

/**
 * @author 12777
 */
public interface TransactionManager {
    long begin();                      // 开启一个新事务
    void commit(long xid);             //提交一个事务
    void abort(long xid);              //取消一个事务
    boolean isActive(long xid);        //查询一个项目是否正在进行
    boolean isCommited(long xid);      //查询一个项目是否提交
    boolean isAborted(long xid);       //查询一个事务是否已取消
    void close();                      //关闭TM

}
