package com.github.welcomeworld.simplebili.net.okhttp.interceptor;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class FixedParameterInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request=chain.request();
        HttpUrl.Builder urlBuilder=request.url().newBuilder();
        urlBuilder.addQueryParameter("build","591204");
        urlBuilder.addQueryParameter("mobi_app","android_b");
        urlBuilder.addQueryParameter("platform","android");
        urlBuilder.addQueryParameter("appkey","07da50c9a0bf829f");
        return chain.proceed(request.newBuilder().url(urlBuilder.build()).build());
    }
}