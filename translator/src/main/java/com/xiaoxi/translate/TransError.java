package com.xiaoxi.translate;

public class TransError {
    public class BaiduErrorCode {
        // 成功
        public static final String SUCCESS = "52000";
        // 请求超时
        public static final String REQUEST_TIMEOUT = "52001";
        // 系统出错
        public static final String SYSTEM_ERROR = "52002";
        // 未授权的用户
        public static final String UNAUTHORIZED_USER = "52003";
        // 必要参数为空
        public static final String REQUISITE_PARAM_EMPTY = "54000";
        // 签名错误
        public static final String SIGATURE_ERROR = "54001";
        // 访问频率过大
        public static final String VISIT_FREQUNT = "54003";
        // 账户余额不足
        public static final String ACCOUNT_BALANCE_INSUFFICIENT = "54004";
        // 长query请求平凡
        public static final String LONGQUERY_REQUEST_FREQUENT = "54005";
        // 客户端ip非法
        public static final String CLIENT_IP_ILLGAL = "58000";
        // 目标语种方向不支持
        public static final String UNSUPPORTED_LANGUAGE = "58001";
        // 服务器当前已关闭
        public static final String SEVER_OVER = "58002";

    }

    public class ErrorCode{
        public static final String UNABLE_CONNECT_SEVER = "00000";
    }
}
