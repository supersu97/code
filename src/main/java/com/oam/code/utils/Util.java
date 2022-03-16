package com.oam.code.utils;


import cn.hutool.core.util.CharsetUtil;

import cn.hutool.http.HttpRequest;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Util {


    public byte[] post(String url, String requestBody){
//        String build = ContentType.build(ContentType.JSON, CharsetUtil.CHARSET_UTF_8);
        byte[] bytes = HttpRequest.post(url).body(requestBody).execute().bodyBytes();
        return bytes;
    }

    public static byte[] sendHttpPost(String url, String requestBody, ContentType contentType) {
//        LoggerUtil.getLogger(context,logger).info("seq:{},请求接口地址:{},请求内容:{}",context.getMsgSeq(),url,requestBody);
        CloseableHttpResponse callback = null;
        byte[] response = null;
        CloseableHttpClient client = null;
        try {
            client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(url);
            RequestConfig config = RequestConfig.custom()
                    .setConnectionRequestTimeout(5000)
                    .setSocketTimeout(5000)
                    .setConnectTimeout(5000).build();
            post.setConfig(config);
            StringEntity entity = new StringEntity(requestBody, contentType);
            post.setEntity(entity);

            callback = client.execute(post);
            if (callback.getStatusLine().getStatusCode() == 200) {
                response = EntityUtils.toByteArray(callback.getEntity());
            }else {
//                logger.error("请求异常，请求状态为：{}",callback.getStatusLine().getStatusCode());
                throw new IOException("请求异常，请求状态为:"+callback.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
//            logger.error("请求失败，error:{}",e.getMessage());
            e.printStackTrace();
        }finally {
            if (callback!= null){
                try {
                    callback.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (client!= null){
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
//        logger.info("请求返回===>seq:{},response:{}",context.getMsgSeq(),response);
        return response;
    }


}
