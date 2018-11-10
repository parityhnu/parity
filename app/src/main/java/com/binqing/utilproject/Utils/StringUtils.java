package com.binqing.utilproject.Utils;

public class StringUtils {
    public static String appendString(String s1, String s2) {
        StringBuilder builder = new StringBuilder();
        builder.append(s1);
        builder.append(s2);
        return builder.toString();
    }
}
