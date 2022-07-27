package com.Element.Music.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaternUtil {

    //手机号正则
    public static boolean isMobile(String mobile) {
        String regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3])|(17[5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(mobile);
        return m.matches();
    }
    // 用户名正则: 大写字母+小写字母+数字+下划线
    public static boolean isUserName(String UserName) {
        String regex = "^([\\u4e00-\\u9fa5]{2,4})|([A-Za-z0-9_]{4,16})|([a-zA-Z0-9_\\u4e00-\\u9fa5]{3,16})$";
        Pattern p = Pattern.compile ( regex, Pattern.CASE_INSENSITIVE );
        Matcher m = p.matcher ( UserName );
        return m.matches ();
    }
    //邮箱正则
    public static boolean isEmail(String Email) {
        String regex = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        Pattern p = Pattern.compile ( regex, Pattern.CASE_INSENSITIVE );
        Matcher m = p.matcher ( Email );
        return m.matches ();
    }
}
