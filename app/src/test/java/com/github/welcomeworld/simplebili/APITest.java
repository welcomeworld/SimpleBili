package com.github.welcomeworld.simplebili;

import com.google.common.eventbus.Subscribe;
import com.google.gson.Gson;
import com.hiczp.bilibili.api.BilibiliAPI;
import com.hiczp.bilibili.api.BilibiliSecurityHelper;
import com.hiczp.bilibili.api.live.socket.LiveClient;
import com.hiczp.bilibili.api.live.socket.entity.DanMuMsgEntity;
import com.hiczp.bilibili.api.live.socket.entity.EnterRoomEntity;
import com.hiczp.bilibili.api.live.socket.event.DanMuMsgPackageEvent;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import okhttp3.HttpUrl;

import static org.junit.Assert.assertEquals;

public class APITest {


    @Test
    public void testLiveDanmaku(){
        /*long roomId = 3;
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            LiveClient liveClient = new BilibiliAPI()
                    .getLiveClient(eventLoopGroup, roomId)
                    .registerListener(new MyListener())
                    .connect();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        try {
            SocketChannel socketChannel=SocketChannel.open(new InetSocketAddress("broadcastlv.chat.bilibili.com",2243));
            ByteBuffer byteBuffer= ByteBuffer.allocate(1024);
            Gson gson=new Gson();
            byte[] bytes=gson.toJson(new EnterRoomEntity(9905913,0)).getBytes();
            byteBuffer.putInt(16+bytes.length);
            byteBuffer.put((byte)0);
            byteBuffer.put((byte)0x10);
            byteBuffer.putInt(0);
            byteBuffer.put((byte)0);
            byteBuffer.put((byte)0x07);
            byteBuffer.putInt(0);
            byteBuffer.put(bytes);
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            while(true){
                System.out.println("before read");
                int readnum=socketChannel.read(byteBuffer);
                if(readnum==-1){
                    System.out.println("Connect is close");
                    break;
                }
                byteBuffer.flip();
                while(byteBuffer.hasRemaining()){
                    System.out.printf("%X ",byteBuffer.get());
                }
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



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
