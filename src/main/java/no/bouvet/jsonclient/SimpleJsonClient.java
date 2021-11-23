package no.bouvet.jsonclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.bouvet.jsonclient.builders.HttpSSLClientBuilder;
import no.bouvet.jsonclient.http.HttpWithHeadersExecuter;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.List;
import java.util.Map;

public class SimpleJsonClient extends JsonClient {



    private JsonConverter jsonConverter;
    private HttpClient httpClient;
    private HttpResponse response;
    private long sleepInMs = 500L;

    public SimpleJsonClient() {
        this.jsonConverter = new JsonConverter();
    }

    public SimpleJsonClient(ObjectMapper objectMapper) {
        this.jsonConverter = new JsonConverter(objectMapper);
    }

    public SimpleJsonClient http() {
        this.httpClient = HttpClientBuilder.create().build();
        return this;
    }

    public SimpleJsonClient ssl() {
        this.httpClient = (new HttpSSLClientBuilder()).build();
        return this;
    }

    public SimpleJsonClient ssl(String username, String password) {
        this.httpClient = (new HttpSSLClientBuilder()).withAuthentication(username, password).build();
        return this;
    }

    public HttpResponse response() {
        return this.response;
    }

    public <T> T object(Class<T> clz) {
        return this.jsonConverter.toObject(this.response.getEntity(), clz);
    }

    public <T> List<T> list(Class<T> clz) {
        return this.jsonConverter.toList(this.response.getEntity(), clz);
    }

    public <T> List<List<T>> listOfList(Class<T> clz) {
        return this.jsonConverter.toListOfList(this.response.getEntity(), clz);
    }

    public <T> Map<String, T> map(Class<T> clz) {
        return this.jsonConverter.toMap(this.response.getEntity(), clz);
    }

    public <T> T poll(String url, Class<T> clz, long timeoutInMs) {
        long startTime = System.currentTimeMillis();
        long endTime = startTime;

        T object;
        for(object = this.executeGet(url, clz); object == null && endTime - startTime < timeoutInMs; endTime = System.currentTimeMillis()) {
            this.threadSleep();
            object = this.executeGet(url, clz);
        }

        return object;
    }

    public <T> T poll(String url, Class<T> clz, long timeoutInMs, Condition<T> condition) {
        long startTime = System.currentTimeMillis();
        long endTime = startTime;
        T object = this.executeGet(url, clz);

        for(boolean isConditionFulfilled = condition.isFulfilled(object); (object == null || !isConditionFulfilled) && endTime - startTime < timeoutInMs; endTime = System.currentTimeMillis()) {
            this.threadSleep();
            object = this.executeGet(url, clz);
            isConditionFulfilled = condition.isFulfilled(object);
        }

        return object;
    }

    public SimpleJsonClient get(String url,Map<String,String> headers) {
        if (this.httpClient != null) {
            this.response = HttpWithHeadersExecuter.get(this.httpClient, url,headers);
            return this;
        } else {
            throw new RuntimeException(this.getHttpClientIsNullError());
        }
    }

    public SimpleJsonClient post(String url, Object object) {
        if (this.httpClient != null) {
            String json = this.jsonConverter.toJson(object);
            this.response = HttpWithHeadersExecuter.post(this.httpClient, url, json);
            return this;
        } else {
            throw new RuntimeException(this.getHttpClientIsNullError());
        }
    }

    public SimpleJsonClient put(String url, Object object) {
        if (this.httpClient != null) {
            String json = this.jsonConverter.toJson(object);
            this.response = HttpWithHeadersExecuter.put(this.httpClient, url, json);
            return this;
        } else {
            throw new RuntimeException(this.getHttpClientIsNullError());
        }
    }

    public SimpleJsonClient delete(String url) {
        if (this.httpClient != null) {
            this.response = HttpWithHeadersExecuter.delete(this.httpClient, url);
            return this;
        } else {
            throw new RuntimeException(this.getHttpClientIsNullError());
        }
    }

    private String getHttpClientIsNullError() {
        return "Http client has not been created. Call method 'http()' or 'ssl()' on your JsonClient";
    }

    private <T> T executeGet(String url, Class<T> clz) {
        HttpResponse response = HttpWithHeadersExecuter.get(this.httpClient, url);
        return this.jsonConverter.toObject(response.getEntity(), clz);
    }

    private void threadSleep() {
        try {
            Thread.sleep(this.sleepInMs);
        } catch (InterruptedException var2) {
            ;
        }

    }
}
