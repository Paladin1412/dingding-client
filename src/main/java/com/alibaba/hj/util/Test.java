package com.alibaba.hj.util;


import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.crypto.Data;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by issuser on 2020/04/07.
 */
public class Test {
//    public void test1() throws NoSuchAlgorithmException {
//        String hash = "";
//        try {
//            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
//            SecretKeySpec secret_key = new SecretKeySpec("".getBytes(), "HmacSHA256");
//            sha256_HMAC.init(secret_key);
//            byte[] bytes = sha256_HMAC.doFinal(message.getBytes());
//            hash = byteArrayToHexString(bytes);
//        } catch (Exception e) {
//            System.out.println("Error HmacSHA256 ===========" + e.getMessage());
//        }
//        String Signature =HmacSha256(SecretKey, bytesToSign);
////      String  bytesToSign = HttpRequestMethod + '\n' + HttpRequestHeaderTimestamp + '\n' + HttpRequestHeaderNonce + '\n' + CanonicalURI + '\n' + HttpRequestParams
//    }


    /**
     * 将加密后的字节数组转换成字符串
     *
     * @param b 字节数组
     * @return 字符串
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toLowerCase();
    }

    /**
     * sha256_HMAC加密
     *
     * @param message 消息
     * @param secret  秘钥
     * @return 加密后字符串
     */
    public static String sha256_HMAC(String message, String secret) {
        String hash = "";
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(message.getBytes());
            hash = byteArrayToHexString(bytes);
        } catch (Exception e) {
            System.out.println("Error HmacSHA256 ===========" + e.getMessage());
        }
        return hash;
    }

    private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:SSS");

    /**
     * 得到UTC时间，类型为字符串，格式为"yyyy-MM-dd HH:mm"<br />
     * 如果获取失败，返回null
     *
     * @return
     */
    public static String getUTCTimeStr() {
        StringBuffer UTCTimeBuffer = new StringBuffer();
        // 1、取得本地时间：
        Calendar cal = Calendar.getInstance();
        // 2、取得时间偏移量：
        int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
        // 3、取得夏令时差：
        int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
        cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        UTCTimeBuffer.append(year).append("-").append(month).append("-").append(day);
        UTCTimeBuffer.append(" ").append(hour).append(":").append(minute);
        try {
            format.parse(UTCTimeBuffer.toString());
            return UTCTimeBuffer.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将UTC时间转换为东八区时间
     *
     * @param UTCTime
     * @return
     */
    public static String getLocalTimeFromUTC(String UTCTime) {
        java.util.Date UTCDate = null;
        String localTimeStr = null;
        try {
            UTCDate = format.parse(UTCTime);
            format.setTimeZone(TimeZone.getTimeZone("GMT-8"));
            localTimeStr = format.format(UTCDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return localTimeStr;
    }

    public static void main(String[] args) throws ParseException {
        String beToSign="GET\\n2020-04-13T11:12:53.581+08:00\\n14170579735813527\\n/gettoken.json\\nappkey=ZZJ-orange-ceshi-t0q3g75qMRzay&appsecret=KX0OBe8oe8f0NFc7i3MSLt8R6E4fA2AsrJwbDT2M";
        String beToSign1="POST\\n2020-04-15T11:12:53.581+08:00\\n14170579735813527\\n/rpc/oauth2/dingtalk_app_user.json\\access_token=app_0c7278fb35024a049ef96d36e2cb0385&auth_code=b43adb2aa9ec418ba5eac8a7a8dfe100019c9901";
        String text = "2020-04-13T11:12:53.581+08:00";
        long timestamp = new Date().getTime();
        String temp = String.valueOf(timestamp);
        StringBuilder builder = new StringBuilder(temp);
        builder.append("1234");
        System.out.println(String.valueOf(timestamp).length() + "\t" + timestamp);
//        String  bytesToSign = HttpRequestMethod + '\n' + HttpRequestHeaderTimestamp + '\n' + HttpRequestHeaderNonce + '\n' + CanonicalURI + '\n' + HttpRequestParams
        String key = "remote-test-0P199w3mEouPVBBnae";
        String param = "GET" + "\n" + builder.toString() + "\n" + text + "\n" + "/gettoken.json" + "\n" + "appkey=ZZJ-orange-ceshi-t0q3g75qMRzay&appsecret=KX0OBe8oe8f0NFc7i3MSLt8R6E4fA2AsrJwbDT2M";
        String sign = sha256_HMAC(key, beToSign1);
        System.out.println("sign=" + sign);
    }
}
