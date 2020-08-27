package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.util.Map;

/**
 * @author dustforest
 */
@Service
public class HttpService {
    private OkHttpClient okHttpClient;
    private DocumentBuilder documentBuilder;
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json;charset=utf-8");
    private static final MediaType MEDIA_TYPE_XML = MediaType.parse("application/xml;charset=utf-8");

    public HttpService() throws Exception {
        okHttpClient = new OkHttpClient();
        documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }

    public DocumentBuilder getDocumentBuilder() {
        return documentBuilder;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    /**
     * get
     * @param url
     * @param headers
     * @return
     */
    public String get(String url, Map<String, String> headers) {
        Request.Builder builder = new Request.Builder();
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                builder.addHeader(header.getKey(), header.getValue());
            }
        }

        Request request = builder.url(url).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.code() >= 400) {
                String msg = "Request " + url + ", " + response.code() + ", " + response.message();
                throw new RuntimeException(msg);
            }
            return response.body() != null ? response.body().string() : null;
        } catch (IOException e) {
            String msg = "Request " + url + " error, " + e.getMessage();
            throw new RuntimeException(msg);
        }
    }

    /**
     * get
     * @param url
     * @param headers
     * @param objectClass
     * @param <T>
     * @return
     */
    public <T> T get(String url, Map<String, String> headers, Class<T> objectClass) {
        return JSON.parseObject(get(url, headers), objectClass);
    }

    /**
     * get
     * @param url
     * @param objectClass
     * @param <T>
     * @return
     */
    public <T> T get(String url, Class<T> objectClass) {
        return get(url, null, objectClass);
    }
}
