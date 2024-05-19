package com.veiculo.api.VeiculoApi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class converteDadosMapper { // converte json para Objeto Java
    private ObjectMapper mapper = new ObjectMapper();

    public <T> T converteDeJsonParaObjeto(String json, TypeReference<T> tipo) throws JsonProcessingException, JsonProcessingException {
        return mapper.readValue(json, tipo);

    }

    public <T> T converteDeJsonParaClasse(String json, Class<T> classe) throws JsonProcessingException {
        return mapper.readValue(json, classe);
    }
}
