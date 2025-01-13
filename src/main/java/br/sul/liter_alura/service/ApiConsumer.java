package br.sul.liter_alura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiConsumer {

    private ApiConsumer(){}

    public static HttpResponse<String> getHttpResponse(String uniformResourceIdentifier) throws IOException, InterruptedException {
        return HttpClient.newHttpClient()
                .send(
                        HttpRequest.newBuilder().uri(URI.create(uniformResourceIdentifier)).build(),
                        HttpResponse.BodyHandlers.ofString()
                );
    }

    public static String getHttpResponseAsString(String uniformResourceIdentifier) throws IOException, InterruptedException {
        return getHttpResponse(uniformResourceIdentifier).body();
    }
}