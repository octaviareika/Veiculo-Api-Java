package com.veiculo.api.VeiculoApi;

import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConversaoDado {

    public ConversaoDado() {}

    public String obterDadosMarca(String codigo) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://parallelum.com.br/fipe/api/v1/carros/marcas/" + codigo + "/modelos")).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject obj = new JSONObject(response.body());

        return obj.toString();
    }

    public String obterDadosCarro(String endereco) throws IOException, InterruptedException {
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco)).build();

        HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        return json;
    }



}
