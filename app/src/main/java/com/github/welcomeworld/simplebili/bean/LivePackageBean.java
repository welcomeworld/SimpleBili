package com.github.welcomeworld.simplebili.bean;

import android.util.Log;

import java.nio.ByteBuffer;

public class LivePackageBean {
    public int type;
    public int packageLength;
    public byte[] content;
    public ByteBuffer getByteBuffer(){
        ByteBuffer byteBuffer=ByteBuffer.allocate(packageLength);
        byteBuffer.putInt(packageLength);
        byteBuffer.putInt(0x100000);
        byteBuffer.putInt(type);
        byteBuffer.putInt(0);
        if(content!=null&&content.length>0){
            byteBuffer.put(content);
        }
        byteBuffer.flip();
        return byteBuffer;
    }
    public LivePackageBean(){
        this(HEART_BEAT,null);
    }

    public LivePackageBean(int type,byte[] content){
        this.type=type;
        this.content=content;
        this.packageLength=content==null?16:16+content.length;
    }
    public static LivePackageBean parse(ByteBuffer byteBuffer) throws Exception {
        if(byteBuffer.remaining()<16){
            Log.e("LivePackageBean","byteBuffer readable data insufficient");
            throw new Exception();
        }
        LivePackageBean livePackageBean=new LivePackageBean();
        livePackageBean.packageLength=byteBuffer.getInt();
        byteBuffer.getInt();
        livePackageBean.type=byteBuffer.getInt();
        byteBuffer.getInt();
        if(livePackageBean.packageLength-16>byteBuffer.remaining()){
            byteBuffer.position(byteBuffer.position()-16);
            Log.e("LivePackageBean","byteBuffer readable data not enough convert to a LivePackageBean");
            throw new Exception();
        }
        livePackageBean.content=new byte[livePackageBean.packageLength-16];
        byteBuffer.get(livePackageBean.content);
        return livePackageBean;
    }

    public static final int HEART_BEAT=0x02;
    public static final int VIEWER_COUNT=0x03;
    public static final int DATA=0x05;
    public static final int ENTER_ROOM=0x07;
    public static final int ENTER_ROOM_SUCCESS=0x08;
}
