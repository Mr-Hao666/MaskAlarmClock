package com.yhao.service;

import com.alibaba.fastjson.JSONObject;
import com.yhao.util.HttpClientUtil;
import com.yhao.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 验证码通知短信接口
 *
 * @ClassName: IndustrySMS
 * @Description: 验证码通知短信接口
 */
@Slf4j
@Component
public class IndustrySMS {
    private static String OPERATION;
    private static String ACCOUNT_SID;
    private static String BASE_URL;
    private static String AUTH_TOKEN;
    private static String RESP_DATA_TYPE;


    @Value("${sms.congif.operation}")
    public void setOperation(String operation) {
        IndustrySMS.OPERATION = operation;
    }

    @Value("${sms.congif.account_sid}")
    public void setAccountSid(String accountSid) {
        ACCOUNT_SID = accountSid;
    }

    @Value("${sms.congif.base_url}")
    public void setBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;
    }

    @Value("${sms.congif.auth_token}")
    public void setAuthToken(String authToken) {
        AUTH_TOKEN = authToken;
    }

    @Value("${sms.congif.resp_data_type}")
    public void setRespDataType(String respDataType) {
        RESP_DATA_TYPE = respDataType;
    }

    /**
     * 构造通用参数timestamp、sig和respDataType
     *
     * @return
     */
    public static String createCommonParam() {
        // 时间戳
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new Date());

        // 签名
        String sig = DigestUtils.md5Hex(ACCOUNT_SID + AUTH_TOKEN + timestamp);
        return "&timestamp=" + timestamp + "&sig=" + sig + "&respDataType=" + RESP_DATA_TYPE;
    }

    /**
     * 验证码通知短信
     */
    public static void execute(String phone, String checkCode) {
        String tmpSmsContent = null;
        String result = null;
        try {
            tmpSmsContent = URLEncoder.encode("【面膜闹钟】登录验证码："+checkCode+"，如非本人操作，请忽略此短信。", "UTF-8");

            String url = BASE_URL + OPERATION;
            String body = "accountSid=" + ACCOUNT_SID + "&to=" + phone + "&smsContent=" + tmpSmsContent
                    + createCommonParam();

            // 提交请求
            result = HttpUtil.post(url, body);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("result:" + System.lineSeparator() + result);
    }
}
