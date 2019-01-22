package com.github.welcomeworld.simplebili.net.okhttp.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class FixedHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder =chain.request().newBuilder();
        requestBuilder.addHeader("Buvid","Yh4vH3pDckBzQQExADZRZlYqGytOd0Z0R3VCinfoc");
        requestBuilder.addHeader("User-Agent","Android/5.0 SimpleBili/1.9.4 (welcomeworld@gmail.com)");
        return chain.proceed(requestBuilder.build());
    }
}
