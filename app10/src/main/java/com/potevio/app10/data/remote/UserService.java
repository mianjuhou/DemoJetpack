package com.potevio.app10.data.remote;

import com.potevio.app10.data.db.User;
import com.potevio.app10.http.param.BaseEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface UserService {

    @POST("users")
    Single<BaseEntity<List<User>>> getUsers(@Body User user);

    ///////pojo///////////////

    /**
     * 实时上传一条路测数据
     * 上传POJO对象
     * POST /mobileLuce/historyUploadLiu
     */
    @POST("historyUploadLiu")
    Single<BaseEntity<String>> uploadRouter(@Body User data);

    ///////form////////////////

    /**
     * 上传字段表单
     */
    @POST("formField")
    @FormUrlEncoded
    Single<BaseEntity<String>> uploadForm(@Field("imsi") String imsi, @Field("time") String time);

    /**
     * 上传Map表单
     */
    @POST("formMap")
    @FormUrlEncoded
    Single<BaseEntity<String>> uploadMap(@FieldMap Map<String, Object> map);

    ///////file////////////

    /**
     *
     */
    @POST("/multipart1")
    @Multipart
    Single<BaseEntity<String>> testMultipart1(@Part("name") RequestBody name, @Part("age") RequestBody age);

    /**
     *
     */
    @POST("/multipart2")
    @Multipart
    Single<BaseEntity<String>> testMultipart2(@PartMap Map<String, RequestBody> map);

    /**
     *
     */
    @POST("/historyUploadFile")
    @Multipart
    Single<BaseEntity<String>> testMultipart3(@Part MultipartBody.Part file);

    ///////////////////////////

    /**
     * 上传文件
     */
    @POST("historyUploadFile")
    @Multipart
    Single<BaseEntity<String>> uploadFile(@Part MultipartBody.Part file);

    @FormUrlEncoded
    @POST("formurl2")
    Single<BaseEntity<String>> testFormUrlEncoded2(@Field("name") String name, @Field("age") int age);

    @FormUrlEncoded
    @POST("formurl1")
    Single<BaseEntity<String>> testFormUrlEncoded1(@FieldMap Map<String, Object> map);

}

