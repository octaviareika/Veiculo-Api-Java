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

    public String obterDadosMarca(String opcao, String codigo) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://parallelum.com.br/fipe/api/v1/" + opcao + "/marcas/" + codigo + "/modelos")).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        System.out.println(json);

        return json;
    }

    public String obterDadosCarro( String endereco) throws IOException, InterruptedException {
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco)).build();

        HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        return json;
    }

    public String obterDadosModelo(String opcao, String codigo, String codigoDeModelo) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request =  HttpRequest.newBuilder()
                .uri(URI.create("https://parallelum.com.br/fipe/api/v1/" + opcao + "/marcas/"
                        + codigo + "/modelos/" + codigoDeModelo + "/anos")).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        return json;

    }

    public String obterDadosFinal(String opcao, String codigo, String tipoAno, String codigoModelo) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://parallelum.com.br/fipe/api/v1/"+ opcao + "/marcas/"+ codigo
                + "/modelos/"+ codigoModelo + "/anos/" + tipoAno)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        return json;
    }

}
