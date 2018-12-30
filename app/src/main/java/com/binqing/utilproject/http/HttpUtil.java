package com.binqing.utilproject.http;

import com.binqing.utilproject.Callback;
import com.binqing.utilproject.Consts.Consts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class HttpUtil {

    //todo 改成通用的方法，remote直接从这里拿，等待测试
    public interface GetService {
        @GET("{path}")
        Call<List<Object>> get(@Path("path") String path, Callback callback);
    }

    public static void get(String path, Callback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.INTERNET_BASEURL)
                .build();

        GetService service = retrofit.create(GetService.class);
        if (callback != null) {
            callback.onResult(service.get(path, callback));
        }
    }

    public static void post() {

    }


}
