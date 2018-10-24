package com.github.welcomeworld.simplebili;

import com.hiczp.bilibili.api.BilibiliSecurityHelper;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import okhttp3.HttpUrl;

import static org.junit.Assert.assertEquals;

public class APITest {
    @Test
    public void testSign(){
        HttpUrl httpUrl = HttpUrl.parse("https://app.bilibili.com/x/resource/show/tab?appkey=1d8b6e7d45233436&build=5320000&mobi_app=android&platform=android&ts=1539248593");
        List<String> nameAndValues = new ArrayList<>(httpUrl.querySize() + 1);
        httpUrl.queryParameterNames().stream()
                .filter(parameterName -> !parameterName.equals("sign"))
                .forEach(name ->
                        httpUrl.queryParameterValues(name).forEach(value -> {
                                    try {
                                        nameAndValues.add(String.format("%s=%s", name, URLEncoder.encode(value, StandardCharsets.UTF_8.toString())));
                                    } catch (UnsupportedEncodingException e) {
                                        throw new Error(e);
                                    }
                                }
                        )
                );
        Collections.sort(nameAndValues);
        assertEquals(httpUrl.newBuilder()
                .encodedQuery(nameAndValues.stream().collect(Collectors.joining("&")))
                .build().toString(),nameAndValues.stream().collect(Collectors.joining("&")));
    }
}
