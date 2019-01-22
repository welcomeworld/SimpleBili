package com.github.welcomeworld.simplebili.net.okhttp.interceptor;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Response;

public class DynamicParameterInterceptor implements Interceptor {
    private Map<String,String> parameters;

    public DynamicParameterInterceptor(Map<String,String> parameters){
        this.parameters=parameters;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        HttpUrl.Builder urlBuilder=chain.request().url().newBuilder();
        if(parameters!=null){
            for(String key:parameters.keySet()){
                urlBuilder.addQueryParameter(key,parameters.get(key));
            }
        }
        return chain.proceed(chain.request().newBuilder().url(urlBuilder.build()).build());
    }
}
