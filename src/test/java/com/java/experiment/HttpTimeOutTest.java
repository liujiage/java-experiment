package com.java.experiment;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.UUID;
@Slf4j
public class HttpTimeOutTest {

    final static HostnameVerifier DO_NOT_VERIFY = (hostname, session) -> true;
    File file = null;
    long TIME_OUT = 8L;
    int HTTP_CONNECT_TIME_OUT = 5;
    int HTTP_READ_TIME_OUT = 5;

    void httpRead() {
        String urlStr = "https://datarepo.eng.ucsd.edu/mcauley_group/data/amazon_v2/categoryFiles/Patio_Lawn_and_Garden.json.gz";
        //String urlStr = "https://speed.hetzner.de/100MB.bin";
        HttpsURLConnection conn = null;
        long startTime = System.currentTimeMillis();
        try {
            URL url = new URL(urlStr);
            file = new File("/Users/jiageliu/tmp/" + LocalDate.now() + UUID.randomUUID());
            conn = (HttpsURLConnection) url.openConnection();
            conn.addRequestProperty("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36");
            conn.setHostnameVerifier(DO_NOT_VERIFY);
            conn.setConnectTimeout(HTTP_CONNECT_TIME_OUT * 1000); //Milliseconds
            conn.setReadTimeout(HTTP_READ_TIME_OUT * 1000); //Milliseconds
            //if the response is not success, then do nothing.
            int responseCode = conn.getResponseCode();
            if(responseCode != HttpURLConnection.HTTP_OK){
                log.error("httpRead connect url: {} is failed!  httpCode: {}", urlStr, responseCode);
                return;
            }
            //read data from stream
            try (InputStream is = conn.getInputStream();
                 OutputStream os = Files.newOutputStream(file.toPath())) {
                byte[] bs = new byte[4096];
                int len;
                while ((len = is.read(bs)) != -1) {
                    boolean timeOut = this.checkTimeout(startTime);
                    if(timeOut) return;
                    os.write(bs, 0, len);
                }
            }
        }catch(Exception e){
            log.error("error", e);
        }finally{
            if(conn != null){
                log.info("HTTP disconnect!");
                conn.disconnect();
            }
            this.deleteFile(file);
        }
    }

    private boolean checkTimeout(long startTime){
        long currentTime = System.currentTimeMillis();
        boolean flag =  ((currentTime - TIME_OUT * 1000L) > startTime);
        if(flag){
            log.warn("time out! startTime: {}, currentTime: {}, timeOutTime: {} timeOutLimit: {} Milliseconds",
                    startTime, currentTime, (currentTime - startTime), TIME_OUT * 1000L);
        }
        return flag;
    }

    // delete file
    private void deleteFile(File file){
        log.info("delete tmp file.");
        try{
            if(file != null && file.exists() && file.isFile()){
                 file.delete();
            }
        }catch (Exception e){
            log.error("delete file error", e);
        }
    }

    @Test
    void test_http_timeout(){
        HttpTimeOutTest httpHandler = new HttpTimeOutTest();
        httpHandler.httpRead();
    }

}
