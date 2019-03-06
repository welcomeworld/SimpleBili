package com.github.welcomeworld.simplebili;

import android.util.Log;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterInputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import io.netty.handler.codec.base64.Base64Encoder;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void test(){
        String source = "This is the SAML String";
        String outcome=null;
        byte[] bytesource = null;
        try {
            bytesource = source.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int byteLength = bytesource.length;
        Deflater compresser = new Deflater();
        compresser.setInput(bytesource);
        compresser.finish();

        byte[] output = new byte[byteLength];
        int compressedDataLength = compresser.deflate(output);
        outcome = new String(output);
        String trimmedoutcome = outcome.trim();
        //String trimmedoutcome = outcome;  // behaves the same way as trimmed;
        // Now try to inflate it
        Inflater decompresser = new Inflater();
        decompresser.setInput(trimmedoutcome.getBytes());
        byte[] result = new byte[4096];
        int resultLength = 0;
        try {
            resultLength = decompresser.inflate(result);
        } catch (DataFormatException e) {
            e.printStackTrace();
        }
        decompresser.end();
        System.out.println("result length ["+resultLength+"]");
        String outputString = null;
        outputString = new String(result, 0, resultLength);
        String returndoc = outputString;
        System.out.println(returndoc);
    }
    @Test
    public void StreamToHex()  {
        int a;
        try{
            URL url=new URL("http://comment.bilibili.com/76534704.xml ");
            URLConnection urlConnection=url.openConnection();
            System.out.println("contentLength"+urlConnection.getContentLength());
            //InputStream inputStream=new InflaterInputStream(urlConnection.getInputStream());
            //InputStream inputStream=new DeflaterInputStream(urlConnection.getInputStream());
            InputStream inputStream=urlConnection.getInputStream();
            System.out.println("StreamData:");
            /*int i=0;
            while((a=inputStream.read())!=-1){
                i++;
                System.out.printf("%X ",a);
            }
            System.out.println("");
            System.out.println("finalLength"+i);*/
            /*ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
            int i=0;
            while((a=inputStream.read())!=-1){
                i++;
                outputStream.write(a);
            }*/
            //ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(Base64.getDecoder().decode(outputStream.toByteArray()));
            InflaterInputStream inflaterInputStream=new InflaterInputStream(inputStream,new Inflater(true));
            ByteArrayOutputStream outputStream1=new ByteArrayOutputStream();
            while((a=inflaterInputStream.read())!=-1){
                System.out.printf("%X ",a);
                outputStream1.write(a);
            }
            System.out.println("finalString"+new String(outputStream1.toByteArray()));
        }catch (Exception e){
            System.out.println("error:"+e.getMessage());
        }
    }
    @Test
    public void addition_isCorrect() {
        try {
            URL url=new URL("http://comment.bilibili.com/77046942.xml ");
            URLConnection urlConnection=url.openConnection();
            InputStream inputStream;
            if(urlConnection.getContentEncoding()!=null&&urlConnection.getContentEncoding().equalsIgnoreCase("GZIP")){
                inputStream=new GZIPInputStream(urlConnection.getInputStream());
                System.out.println("GZIP");
            }else if(urlConnection.getContentEncoding()!=null&&urlConnection.getContentEncoding().equalsIgnoreCase("DEFLATE")){
                inputStream=new InflaterInputStream(urlConnection.getInputStream());
                System.out.println("DEFLATE");
            }else{
                inputStream=urlConnection.getInputStream();
                System.out.println("ORINAL");
            }
            int i;
            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            while((i=inputStream.read())!=-1){
                byteArrayOutputStream.write(i);
            }
            String a=byteArrayOutputStream.toString();
            assertEquals("<?xml",a);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}