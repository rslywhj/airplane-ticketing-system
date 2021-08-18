package com.tjetc.common;

public enum MethodOperation {
    /**
     * 登录
     */
    LOGIN,
    /**
     * 注册
     */
    JOIN,
    /**
     * 机票查询
     */
    Ticket_inquiry,
    /**
     * 班次查询
     */
    CLASSES_Query,
    /**
     * 航班查询
     */
    Flight_Query,
    /**
     * 购买
     */
    BUY,
    /**
     * 管理员登录
     */
    ADMINLOGIN,
    /**
     * 延误通知
     */
    DelayNotice,
    /**
     * 设置延误
     */
    DelaySet,
    /**
     * 航班增加
     */
    ADDFlight,
    /**
     * 航班取消
     */
    DELETEFlight,
    /**
     * 班次增加
     */
    ADDClasses,
    /**
     * 班次减少
     */
    DELETEClasses,
    /**
     * 订单查询
     */
    OrderQuery,
    /**
     * 报表查看
     */
    Report;
}
