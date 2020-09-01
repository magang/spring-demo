package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(HttpService.class);

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
                logger.error(msg, response.body());
                throw new RuntimeException(msg);
            }
            return response.body() != null ? response.body().string() : null;
        } catch (IOException e) {
            String msg = "Request " + url + " error, " + e.getMessage();
            logger.error(msg, e);
            throw new RuntimeException(msg);
        }
    }

    public <T> T get(String url, Map<String, String> headers, Class<T> objectClass) {
        return JSON.parseObject(get(url, headers), objectClass);
    }

    public <T> T get(String url, Class<T> objectClass) {
        return get(url, null, objectClass);
    }

    public String post(String url, Map<String, String> headers, RequestBody requestBody) {
        Request.Builder builder = new Request.Builder();
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                builder.addHeader(header.getKey(), header.getValue());
            }
        }

        Request request = builder.url(url).post(requestBody).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.code() >= 400) {
                String msg = "Request " + url + ", " + response.code() + ", " + response.message();
                logger.error(msg, response.body());
                throw new RuntimeException(msg);
            }
            return response.body() != null ? response.body().string() : null;
        } catch (IOException e) {
            String msg = "Request " + url + " error, " + e.getMessage();
            logger.error(msg, e);
            throw new RuntimeException(msg);
        }
    }

    public String post(String url, Map<String, String> headers, String json) {
        RequestBody requestBody = RequestBody.create(json, MEDIA_TYPE_JSON);
        return post(url, headers, requestBody);
    }

    public String postXml(String url, Map<String, String> headers, String xml) {
        RequestBody requestBody = RequestBody.create(xml, MEDIA_TYPE_XML);
        return post(url, headers, requestBody);
    }

    public String postForm(String url, Map<String, String> headers, Map<String, String> formData) {
        FormBody.Builder builder = new FormBody.Builder();
        if (formData != null && formData.size() > 0) {
            for (Map.Entry<String, String> entry : formData.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        return post(url, headers, builder.build());
    }

    public <T> T post(String url, Map<String, String> headers, String json, Class<T> objectClass) {
        return JSON.parseObject(post(url, headers, json), objectClass);
    }

    public <T> T post(String url, String json, Class<T> objectClass) {
        return post(url, null, json, objectClass);
    }

    public String put(String url, Map<String, String> headers, RequestBody requestBody) {
        Request.Builder builder = new Request.Builder();
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                builder.addHeader(header.getKey(), header.getValue());
            }
        }

        Request request = builder.url(url).put(requestBody).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.code() >= 400) {
                String msg = "Request " + url + ", " + response.code() + ", " + response.message();
                logger.error(msg, response.body());
                throw new RuntimeException(msg);
            }
            return response.body() != null ? response.body().string() : null;
        } catch (IOException e) {
            String msg = "Request " + url + " error, " + e.getMessage();
            logger.error(msg, e);
            throw new RuntimeException(msg);
        }
    }

    public String put(String url, Map<String, String> headers, String json) {
        RequestBody requestBody = RequestBody.create(json, MEDIA_TYPE_JSON);
        return put(url, headers, requestBody);
    }

    public String putXml(String url, Map<String, String> headers, String xml) {
        RequestBody requestBody = RequestBody.create(xml, MEDIA_TYPE_XML);
        return put(url, headers, requestBody);
    }

    public String putForm(String url, Map<String, String> headers, Map<String, String> formData) {
        FormBody.Builder builder = new FormBody.Builder();
        if (formData != null && formData.size() > 0) {
            for (Map.Entry<String, String> entry : formData.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        return put(url, headers, builder.build());
    }

    public <T> T put(String url, Map<String, String> headers, String json, Class<T> objectClass) {
        return JSON.parseObject(put(url, headers, json), objectClass);
    }

    public <T> T put(String url, String json, Class<T> objectClass) {
        return put(url, null, json, objectClass);
    }

    public String delete(String url, Map<String, String> headers) {
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
                logger.error(msg, response.body());
                throw new RuntimeException(msg);
            }
            return response.body() != null ? response.body().string() : null;
        } catch (IOException e) {
            String msg = "Request " + url + " error, " + e.getMessage();
            logger.error(msg, e);
            throw new RuntimeException(msg);
        }
    }

    public <T> T delete(String url, Map<String, String> headers, Class<T> objectClass) {
        return JSON.parseObject(delete(url, headers), objectClass);
    }

    public <T> T delete(String url, Class<T> objectClass) {
        return delete(url, null, objectClass);
    }
}
