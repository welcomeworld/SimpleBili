package com.github.welcomeworld.simplebili.utils;

import android.os.Environment;
import android.util.Log;
import android.util.SparseArray;

import com.github.welcomeworld.simplebili.MApplication;
import com.github.welcomeworld.simplebili.bean.DownloadInfoBean;
import com.github.welcomeworld.simplebili.bean.UpdateBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadManager {

    private OkHttpClient mClient;
    private SparseArray<Call> downCalls;
    public static SparseArray<DownloadInfoBean> data = new SparseArray<>();
    private static DownloadManager instance;
    private List<DownloadListener>  listeners =  new ArrayList<>();

    public void setApplication(MApplication application) {
        this.application = application;
    }

    private MApplication application;


    private DownloadManager(){
        downCalls = new SparseArray<>();
        mClient = new OkHttpClient.Builder()
                .connectTimeout(10L, TimeUnit.SECONDS)
                .writeTimeout(10L, TimeUnit.SECONDS)
                .readTimeout(10L, TimeUnit.SECONDS)
                .build();
    }

    public static DownloadManager getInstance(){
        if(instance == null){
            instance = new DownloadManager();
        }
        return instance;
    }

    public void startDownload(DownloadInfoBean info){
        if(downCalls.get(info.getDownloadId())!=null){
            Disposable observable = Observable.create(new ObservableOnSubscribe<DownloadInfoBean>() {
                @Override
                public void subscribe(ObservableEmitter<DownloadInfoBean> emitter){
                    InputStream is = null;
                    FileOutputStream fileOutputStream = null;
                    try {
                    Response response = downCalls.get(info.getDownloadId()).execute();
                    if(response.body() == null){
                        info.setDownloadState(DownloadInfoBean.ERROR);
                        emitter.onNext(info);
                        return;
                    }
                        if(info.getContentLength()==-1){
                            info.setContentLength(response.body().contentLength());
                            if(application!=null){
                                try {
                                    application.getDatabase().getDao().updateDownload(info);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }
                    File file = new File(info.getLocalPath());
                    if(!file.exists()){
                        file.createNewFile();
                    }
                        emitter.onNext(info);
                    long downloadLength = file.length();
                        is = response.body().byteStream();
                        fileOutputStream = new FileOutputStream(file, true);
                        byte[] buffer = new byte[2048];//缓冲数组2kB
                        int len;
                        while ((len = is.read(buffer)) != -1) {
                            fileOutputStream.write(buffer, 0, len);
                            downloadLength += len;
                            emitter.onNext(info);
                        }
                        fileOutputStream.flush();
                        if(file.length()>info.getContentLength()){
                            info.setContentLength(file.length());
                            if(application!=null){
                                try {
                                    application.getDatabase().getDao().updateDownload(info);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }
                        info.setDownloadState(DownloadInfoBean.COMPLETE);
                        emitter.onNext(info);
                        downCalls.remove(info.getDownloadId());
                        data.remove(info.getDownloadId());
                    }catch (Exception e){
                        if(info.getDownloadState() != DownloadInfoBean.PAUSE){
                            info.setDownloadState(DownloadInfoBean.ERROR);
                            emitter.onNext(info);
                        }
                        e.printStackTrace();
                    }
                    finally {
                        //关闭IO流
                        if(is !=null){
                            try {
                                is.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if(fileOutputStream!=null){
                            try {
                                fileOutputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<DownloadInfoBean>() {
                @Override
                public void accept(DownloadInfoBean aLong) throws Exception {
                    if(aLong.getDownloadState() == DownloadInfoBean.DOWNLOADING){
                        for(DownloadListener listener:listeners){
                            listener.onDownloading(info);
                        }
                    }else if(aLong.getDownloadState() == DownloadInfoBean.PREPARED){
                        info.setDownloadState(DownloadInfoBean.DOWNLOADING);
                        for(DownloadListener listener:listeners){
                            listener.onStart(info);
                        }
                    }else if(aLong.getDownloadState() == DownloadInfoBean.COMPLETE){
                        for(DownloadListener listener:listeners){
                            listener.onCompleted(info);
                        }
                    } else if(aLong.getDownloadState() == DownloadInfoBean.ERROR){
                        for(DownloadListener listener:listeners){
                            listener.onError(info);
                        }
                    }
                }
            });
        }
    }

    public void pauseDownload(DownloadInfoBean info){
        info.setDownloadState(DownloadInfoBean.PAUSE);
        for(DownloadListener listener:listeners){
            listener.onPause(info);
        }
        if(data.get(info.getDownloadId())!=null){
            data.remove(info.getDownloadId());
        }
        Call call = downCalls.get(info.getDownloadId());
        if(call!=null){
            call.cancel();
        }
        downCalls.remove(info.getDownloadId());
    }

    public void preparedDownload(DownloadInfoBean info){
        info.setDownloadState(DownloadInfoBean.PREPARED);
        if(info.getDownloadId()==-1){
            info.setDownloadId(UUID.randomUUID().hashCode());
        }
        data.append(info.getDownloadId(),info);
        long downloadLength =0;
        if(info.getLocalPath()==null||info.getLocalPath().isEmpty()){
            File path = new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+"/SimpleBili");
            if(!path.exists()){
                path.mkdirs();
            }
            File file = new File(path.getPath(),info.getTitle());
            if(file.exists()){
                downloadLength  = file.length();
            }
            info.setLocalPath(file.getAbsolutePath());
        }else {
            File file = new File(info.getLocalPath());
            if(file.exists()){
                downloadLength  = file.length();
            }
        }
        Request request = new Request.Builder()
                //确定下载的范围,添加此头,则服务器就可以跳过已经下载好的部分
                .addHeader("RANGE", "bytes=" + downloadLength + "-")
                .url(info.getSourceUrl())
                .build();
        downCalls.append(info.getDownloadId(),mClient.newCall(request));
        if(info.getDownloadState() == DownloadInfoBean.PREPARED){
            for(DownloadListener listener:listeners){
                listener.onPrepared(info);
            }
            if(application!=null){
                try {
                    application.getDatabase().getDao().setDownload(info);
                }catch (Exception e){
                    application.getDatabase().getDao().updateDownload(info);
                }
            }
            startDownload(info);
        }
    }

    public void cancelDownload(DownloadInfoBean info){
        for(DownloadListener listener:listeners){
            listener.onCanecl(info);
        }
        if(data!=null){
            data.remove(info.getDownloadId());
        }
        if(downCalls.get(info.getDownloadId())!=null){
            downCalls.get(info.getDownloadId()).cancel();
        }
    }

    public void refreshSourceUrl(DownloadInfoBean info){

    }

    public static abstract class DownloadListener{
        public void onPrepared(DownloadInfoBean info){
            Log.e("DownloadManager","prepared");
        }
        public void onStart(DownloadInfoBean info){
            Log.e("DownloadManager","start");
        }
        public void onDownloading(DownloadInfoBean info){
            Log.e("DownloadManager","downloading");
        }
        public void onPause(DownloadInfoBean info){
            Log.e("DownloadManager","pause");
        }
        public void onCanecl(DownloadInfoBean info){
            Log.e("DownloadManager","cancel");
        }

        public void onError(DownloadInfoBean info){
            Log.e("DownloadManager","error");
        }

        public void onCompleted(DownloadInfoBean info){
            Log.e("DownloadManager","completed");
        }
    }

    public void addDownloadListener(DownloadListener listener){
        listeners.add(listener);
    }

    public void removeDownloadListener(DownloadListener listener){
        listeners.remove(listener);
    }

    public void downloadUpdate(UpdateBean updateBean,Observer<Long> observer){
        long downloadLength =0;
        File path = new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+"/SimpleBili/update");
        if(!path.exists()){
            path.mkdirs();
        }
        File file = new File(path.getPath(),updateBean.getVersionName()+".apk");
        if(file.exists()){
            downloadLength  = file.length();
        }
        Request request = new Request.Builder()
                //确定下载的范围,添加此头,则服务器就可以跳过已经下载好的部分
                .addHeader("RANGE", "bytes=" + downloadLength + "-")
                .url(updateBean.getPath())
                .build();
        Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
                InputStream is = null;
                FileOutputStream fileOutputStream = null;
                try {
                    Response response = mClient.newCall(request).execute();
                    if(!file.exists()){
                        file.createNewFile();
                    }
                    long downloadLength = file.length();
                    is = response.body().byteStream();
                    fileOutputStream = new FileOutputStream(file, true);
                    byte[] buffer = new byte[2048];//缓冲数组2kB
                    int len;
                    while ((len = is.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, len);
                        downloadLength += len;
                        emitter.onNext(downloadLength);
                    }
                    emitter.onComplete();
                    fileOutputStream.flush();
                }catch (Exception e){
                    throw e;
                }finally {
                    fileOutputStream.close();
                    is.close();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
}
