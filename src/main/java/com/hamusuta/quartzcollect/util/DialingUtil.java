package com.hamusuta.quartzcollect.util;

import com.hamusuta.quartzcollect.job.HttpBaseJob;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.apache.http.impl.client.HttpClients;

import java.net.URI;
import java.net.URL;

/**
 * http ping测工具
 */
public class DialingUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpBaseJob.class);

    private static final int CONNECT_TIME_OUT = 5 * 1000;
    private static final int REQUEST_TIME_OUT = 15 * 1000;
    private static final int SOCKET_TIME_OUT = 60 * 1000;

    /**
     * get
     * @param url
     * @return 0断线 1在线
     */
    public static Integer dialingTest(String url){
        logger.info("dialingTest,get,url:{}", url);
        Integer successCode = 0;
        try (CloseableHttpClient client = HttpClients.createDefault()){
            URL tranUrl = new URL(url);
            URI uri = new URI(tranUrl.getProtocol(), tranUrl.getUserInfo(), tranUrl.getHost(), tranUrl.getPort(), tranUrl.getPath(), tranUrl.getQuery(), null);
            url = uri.toURL().toString();
            HttpGet httpGet = new HttpGet(url);
            RequestConfig conf = RequestConfig.custom()
                    .setConnectTimeout(CONNECT_TIME_OUT)
                    .setSocketTimeout(SOCKET_TIME_OUT)
                    .setConnectionRequestTimeout(REQUEST_TIME_OUT).build();
            httpGet.setConfig(conf);
            httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36");
            HttpResponse response = client.execute(httpGet);
            logger.debug("dialingTest,execute ended,http status:{}",response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
                successCode = 1;
            }
        }catch (Exception e){
            logger.info("dialingTest,get,Exception:{}", e);
        }
        return successCode;
    }
}
