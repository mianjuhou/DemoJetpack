package com.potevio.app10.http;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class OkHttpUtil {

    public static final int[][] TIME_OUT = {{10, 10, 10}, {30, 30, 30}};
    private static OkHttpClient client;
    private static OkHttpClient longClient;

    private static OkHttpClient createClient(int[] timeOut) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //超时
        builder.connectTimeout(timeOut[0], TimeUnit.SECONDS);
        builder.connectTimeout(timeOut[1], TimeUnit.SECONDS);
        builder.connectTimeout(timeOut[2], TimeUnit.SECONDS);
        //缓存
//        builder.cache(new Cache(FileUtil.getCacheFile(), 10 * 1024 * 1024));
        //拦截
        ////加日志
//        builder.addInterceptor(loggingInterceptor);
        ////加token
//        builder.addInterceptor(new Interceptor() {
//            @NotNull
//            @Override
//            public Response intercept(@NotNull Chain chain) throws IOException {
//                Request request = chain.request()
//                        .newBuilder()
//                        .addHeader("token", memberId)
//                        .build();
//                return chain.proceed(request);
//            }
//        });
        ////加UUID
//        builder.addInterceptor(new Interceptor() {
//            @NotNull
//            @Override
//            public Response intercept(@NotNull Chain chain) throws IOException {
//                Request request = chain.
//                        request()
//                        .newBuilder()
//                        .addHeader("uuid", UUIDUtil.getDeviceUUID())
//                        .build();
//                return chain.proceed(request);
//            }
//        })
        //添加证书
//        builder.sslSocketFactory();
        return builder.build();
    }

    public static OkHttpClient getClient() {
        if (client == null) {
            client = createClient(TIME_OUT[0]);
        }
        return client;
    }

    public static OkHttpClient getLongClient() {
        if (longClient == null) {
            longClient = createClient(TIME_OUT[1]);
        }
        return longClient;
    }

    /**
     * 创建上传文件所需请求体
     */
    public static MediaType fileType = MediaType.parse("application/octet-stream");

    public static MultipartBody.Part createFileBody(String name, File file) {
        MultipartBody.Part part = MultipartBody.Part.createFormData(name, file.getName(), RequestBody.create(fileType, file));
        return part;
    }

    public static RequestBody createFileRequstBody(String name, File file) {
        MultipartBody requestBody = new MultipartBody.Builder()
                .addFormDataPart(name, file.getName(), RequestBody.create(fileType, file))
                .build();
        return requestBody;
    }


}
