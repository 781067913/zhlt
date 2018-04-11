package com.gy.util;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/3/23 13:45
 */
public class Constant {

    //------------------------------------------------WORKORDER-报工单-----------------------------------------------------------
    public static final String WORK_ORDER_PROBLEM_YES="1";//重要！！！！！报工单中问题申报合格的参数项的id


    //------------------------------------------------返回值------------------------------------------------------------
    public static final Integer RESULT_CODE_SUCCESS=200;

    public static final Integer RESULT_CODE_FALSE=300;//业务错误，如果返回值是该值，则直接把错误信息展示给用户

    public static final Integer RESULT_CODE_ERROR=400;

    public static final Integer RESULT_CODE_LOGIN=500;//重新登录

    public static final String RESULT_MESSAGE_SUCCESS="操作成功";

    public static final String RESULT_MESSAGE_FALSE="操作失败";

    public static final String RESULT_MESSAGE_ERROR="系统错误";

    public static final String RESULT_MESSAGE_LOGIN="请重新登录";//重新登录



    //------------------------------------------------车胎流程状态------------------------------------------------------------
    public static final String TYRE_PROCESS_STATUS_RZK="1";//入总库

    public static final String TYRE_PROCESS_STATUS_WAIT="3";//分库待用

    public static final String TYRE_PROCESS_STATUS_INSTALL="4";//安装

    //------------------------------------------------Redis------------------------------------------------------------

    public static final String REDIS_USER_KEY_HEAD="USER-";//缓存用户头

    public static final Long REDIS_LIMIT_SECOND=60*60*3L;


    //------------------------------------------------MYLOG日记------------------------------------------------------------
    public static final String MYLOG_MESSAGE_GET="入总库";
    public static final String MYLOG_TYPE_GET="1";

    public static final String MYLOG_MESSAGE_INSTALL="安装";
    public static final String MYLOG_TYPE_INSTALL="4";

    public static final String MYLOG_MESSAGE_UNINSTALL="卸载";
    public static final String MYLOG_TYPE_UNINSTALL="9";

    public static final String MYLOG_MESSAGE_CHECK_DEPTH="趟检-录入纹深";
    public static final String MYLOG_TYPE_CHECK_DEPTH="5";

    public static final String MYLOG_MESSAGE_CHECK_WORKORDER="趟检-生成报工单";
    public static final String MYLOG_TYPE_CHECK_WORKORDER="6";

    public static final String MYLOG_MESSAGE_DEAL_WORKORDER="处理报工单";
    public static final String MYLOG_TYPE_DEAL_WORKORDER="7";


    //------------------------------------------------TYRESTATUS 轮胎状态------------------------------------------------------------
    public static final String TYRE_STATUS_YES="1";//轮胎状态合格

    public static final String TYRE_STATUS_NO="0";//轮胎状态不合格
}
