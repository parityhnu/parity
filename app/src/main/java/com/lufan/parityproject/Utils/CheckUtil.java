package com.lufan.parityproject.Utils;

public class CheckUtil {
    public static boolean checkPassword(String password) {
        if (password == null || "".equals(password) || password.length() < 6 || password.length() > 12) {
            return false;
        }
        // Z = 字母       S = 数字           T = 特殊字符
        String regex = "^(?![\\d]+$)(?![a-zA-Z]+$)(?![^\\da-zA-Z]+$).{6,12}$";
        if (password.matches(regex)) {
            return true;
        }
        return false;
    }

    public static boolean checkPhone(String phone) {
        if (phone == null || "".equals(phone) || phone.length() != 11) {
            return false;
        }
        // Z = 字母       S = 数字           T = 特殊字符
        String regexS = "^\\d+$";
        if (phone.matches(regexS)) {
            return true;
        }
        return false;
    }

    public static boolean checkName(String name) {
        if (name == null || "".equals(name) ||  name.length() > 12) {
            return false;
        }
        return true;
    }

    public static boolean checkAccount(String account) {
        if (account == null || "".equals(account)) {
            return false;
        }
        if (!((account.getBytes()[0] >= 97 && account.getBytes()[0] <= 122) || (account.getBytes()[0] >= 65 && account.getBytes()[0] <= 90))) {
            return false;
        }
        String regexZS = "[0-9A-Za-z]+";
        if (account.matches(regexZS)) {
            return true;
        }
        return false;
    }
}
