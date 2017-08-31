package com.hulu;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 业务接口测试
 * @author likaige
 * @date 2017-03-04.
 */
public class Main {
    private static Logger log = LoggerFactory.getLogger(HttpRequestUtils.class);

    // 加密的盐
    public static final String DES_SALT = "XgYsd_JVBs+!@#$%Nfd+_-1jjb!//^&*()";

    //开发环境业务域名
    private static final String BASE_URL = "https://dev.if-chat.com:9000/api/";

    //用户登录的token
    public static String token;
    public static String key;
    public static String iv;

    @Before
    public void init() {
        log.info("init");
        this.sms_codev1();
        this.loginv1();
    }

    @Test
    public void sms_codev1() {
        log.info("sms_codev1...");
        String url = BASE_URL + "login/sms_codev1";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phone_number", "13041092162");
        jsonObject.put("device_id", "1234");

        HttpRequestUtils.httpPost(url, jsonObject, null, null);
    }


    @Test
    public void loginv1() {
        log.info("loginv1...");

        String url = BASE_URL + "login/loginv1";

        String code = new SimpleDateFormat("MMdd").format(new Date());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phone_number", "13041092162");
        jsonObject.put("code", code);

        JSONObject resultJsonObject = HttpRequestUtils.httpPost(url, jsonObject, null, null);
        log.info("resultJsonObject=={}", resultJsonObject);

        JSONObject data = resultJsonObject.getJSONObject("data");

        token = data.getString("token");
        String uid = data.getString("uid");
        log.info("token=={}", token);
        log.info("uid=={}", uid);

        String md5str = MD5Utils.md5(uid + DES_SALT + token);
        key = md5str.substring(0,24);
        iv = md5str.substring(24,32);

        log.info("key={} iv={}", key, iv);
    }


    @Test
    public void personalpagev1() throws UnsupportedEncodingException {
        log.info("personalpagev1... token={} key={} iv={}", token, key, iv);


        log.info("token1====={}", token);
        //token = URLEncoder.encode(token, "UTF-8");
        //log.info("token2====={}", token);

        //String url = BASE_URL + "personal/personalpagev1?token=" + token;
        String url = BASE_URL + "personal/personalpagev1?token=" + URLEncoder.encode(token, "UTF-8");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageuid", "100510");
        jsonObject.put("lastmsgid", "0");

        JSONObject resultJsonObject = HttpRequestUtils.httpPost(url, jsonObject, key, iv);
        log.info("resultJsonObject=={}", resultJsonObject);
    }






    public static void main(String[] args) {
        String url = "https://dev.if-chat.com:9000/api/login/sms_codev1";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phone_number", "13041092162");
        jsonObject.put("device_id", "1234");

        //HttpRequestUtils.httpPost(url, jsonObject);
    }
}
