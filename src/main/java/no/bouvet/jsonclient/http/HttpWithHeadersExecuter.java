package no.bouvet.jsonclient.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import java.util.Map;

public class HttpWithHeadersExecuter extends HttpExecuter {

    public static HttpResponse get(HttpClient httpClient, String url,Map<String,String> headers) {
        return execute(httpClient, createHttpGet(url),headers);
    }


    private static HttpGet createHttpGet(String url) {
        return new HttpGet(url);
    }


    private static HttpResponse execute(HttpClient httpClient, HttpUriRequest request, Map<String,String> headers) {
        try {
            headers.forEach((s, s2) -> request.setHeader(s,s2));

            return httpClient.execute(request);
        } catch (Exception var3) {
            throw new RuntimeException("Error when executing " + request, var3);
        }
    }

}
