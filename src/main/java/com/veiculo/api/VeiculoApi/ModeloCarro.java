package com.veiculo.api.VeiculoApi;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.Id;

@JsonIgnoreProperties(ignoreUnknown = true)

public class ModeloCarro {

    @JsonAlias("nome") private String modelo;
    @JsonAlias("codigo") private String codigo;

    public ModeloCarro() {}

    public ModeloCarro(String modelo) {
        this.modelo = modelo;
    }
    public ModeloCarro(String modelo, String codigo) {
        this.modelo = modelo;
        this.codigo = codigo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "modelo=" + modelo + ", codigo=" + codigo + "\n";
    }


}
