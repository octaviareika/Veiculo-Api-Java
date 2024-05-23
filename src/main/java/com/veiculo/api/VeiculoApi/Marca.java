package com.veiculo.api.VeiculoApi;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.annotation.Generated;
import jakarta.*;


@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "marca")
public class Marca {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonAlias("nome") 
    private String nome;
    @JsonAlias("codigo") 
    private String codigo;

    // Uma marca de veículos tem vários modelos
    @OneToMany(mappedBy = "marca")
    private List<ModeloCarro> modelos;

    public Marca() {
        modelos = new ArrayList<>();
    }

    public Marca(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
        modelos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        return "Marca = " +
                "nome='" + nome + '\'' +
                ", codigo='" + codigo + '\'' +
                '\n';
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<ModeloCarro> getModelos() {
        return modelos;
    }

    public void setModelos(List<ModeloCarro> modelos) {
        this.modelos = modelos;
    }

    public void addModelo(ModeloCarro modelo){ // precisa ter 
            modelos.add(modelo);
        

    }
}
