package com.hengmeng.hmkuaiyi.pro.function.translate.api;

import com.xiaoxi.translate.api.TransUrl;

import java.util.HashMap;
import java.util.Map;

public class TransApi {
    private String appid;
    private String securityKey;

    public TransApi(String appid, String securityKey) {
        this.appid = appid;
        this.securityKey = securityKey;
    }

    public String getTransResult(String query, String from, String to) {
		Map<String, String> params = buildParams(query, from, to);
        return com.hengmeng.hmkuaiyi.pro.function.translate.api.HttpGet.get(TransUrl.TRANS_API_HOST, params);
    }

    private Map<String, String> buildParams(String query, String from, String to) {
        Map<String, String> params = new HashMap<>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);

        params.put("appid", appid);

        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);

        // 签名
        String src = appid + query + salt + securityKey; // 加密前的原文
        params.put("sign", com.hengmeng.hmkuaiyi.pro.function.translate.api.MD5.md5(src));

        return params;
    }

}
