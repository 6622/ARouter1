package com.czq.module_common.network.retrofitrxok.interfacepath;




import com.czq.module_common.network.retrofitrxok.Data;

import org.json.JSONObject;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 网络Service
 */
public interface UserService {

    /*登录请求接口start*/
    @FormUrlEncoded
    @POST(Data.LoginFast)
    Observable<JSONObject> LoginFast(@FieldMap Map<String, String> key);
    @FormUrlEncoded
    @POST(Data.Loginwx)
    Observable<JSONObject> Login(@FieldMap Map<String, String> key);

    @Multipart
    @POST(Data.Upload)
    Observable<ResponseBody> upload(@Part MultipartBody.Part file);

    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);
    /*登录请求接口end*/


}
