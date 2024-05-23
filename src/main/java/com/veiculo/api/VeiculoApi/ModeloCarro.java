package com.veiculo.api.VeiculoApi;



import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.annotation.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.Id;

@JsonIgnoreProperties(ignoreUnknown = true)

@Entity
@Table(name = "ModeloVeiculo")
public class ModeloCarro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonAlias("nome")
    private String modelo;
    @JsonAlias("codigo") 
    private String codigo;

    // Um modelo de veículo pertence a uma marca
    @ManyToOne
    private Marca marca;
    

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

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Marca getMarca() {
        return marca;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }




}
