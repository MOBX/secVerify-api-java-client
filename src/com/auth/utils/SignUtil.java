package com.auth.utils;

import java.util.Map;
import java.util.TreeMap;

/**
 * SignUtil
 * Date: 2019/6/24
 * Time: 15:11
 *
 * @author tangwch
 */
public class SignUtil {
    private static String charset = "utf8";

    public static String getSign(Map<String, Object> data, String secret) {
        if (data == null) {
            return null;
        }
        //排序参数
        Map<String, Object> mappingList = new TreeMap<>(data);
        StringBuilder plainText= new StringBuilder();
        mappingList.forEach((k, v) -> {
            if (!"sign".equals(k) && !BaseUtils.isEmpty(v)) {
                plainText.append(String.format("%s=%s&", k, v));
            }
        });
        String substring = plainText.substring(0, plainText.length() - 1);
        return Md5Util.MD5Encode(substring + secret, charset);
    }
}
