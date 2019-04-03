package com.binqing.utilproject.Consts;

public class Consts {

    //数据库相关
    public static int DATABASE_VERSION = 3;
    public static String DATABASE_NAME = "util_project_database.db";
    public static String DATABASE_PRIMARY_KEY_NAME = "p_id";
    public static String DATABASE_SQLITE_MASTER_TABLE_NAME = "s_master";
    public static String DATABASE_SQLITE_MASTER_Column_NAME = "s_name";

    //网络请求相关
    public static String INTERNET_BASEURL = "http://10.0.2.2:9090/";
    public static String INTERNET_PATH = "";

    //intent
    public static String INTENT_KEY_SEARCH_GOODS_HINT_NAME = "intent_key_search_goods_hint_name";
    public static String INTENT_KEY_SEARCH_GOODS_NAME = "intent_key_search_goods_name";
    public static String INTENT_KEY_PARITY_GOODS_DETAIL = "intent_key_parity_goods_detail";
    public static String INTENT_KEY_MODIFY_TYPE = "intent_key_modify_type";
    public static String INTENT_KEY_WEBVIEW_HREF = "intent_key_webview_href";

    //sp，不准带有数字
    public static String PREDERENCE_SEARCH_HISTORY = "preference_search_history";
    public static String PREDERENCE_USERID = "prederence_user_id";
    public static String PREDERENCE_ACCOUNT_NAME = "prederence_account_name";
    public static String PREDERENCE_PHONE = "prederence_phone";
}
