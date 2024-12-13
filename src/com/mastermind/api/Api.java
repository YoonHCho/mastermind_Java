package com.mastermind.api;

import com.mastermind.ui.OutputHandler;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Api {
    public Api() {}

    static public String getCode(int lvl) {
        String code;
        try {
            // create HttpClient instance
            HttpClient client = HttpClient.newHttpClient();

            // concat the user selected level for the api
            String uriStr = "https://www.random.org/integers/?num=" + lvl + "&min=0&max=7&col=1&base=10&format=plain&rnd=new";

            // create a request
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uriStr)).build();

            // send the request and get response
            OutputHandler.printResult("Sending request, waiting for the response");
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            code = response.body().replaceAll("\n", "");
            return code;
        } catch (Exception e) {
            OutputHandler.printResult(e.getMessage());
            throw new Error("Please check the if API is working and try again, exiting game.");
        }
    }
}
