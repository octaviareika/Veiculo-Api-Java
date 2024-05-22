package com.veiculo.api.VeiculoApi.TipoVeiculo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.veiculo.api.VeiculoApi.AnoCarro;
import com.veiculo.api.VeiculoApi.Marca;
//import com.veiculo.api.VeiculoApi.MarcaCarro;
import com.veiculo.api.VeiculoApi.ModeloCarro;

/*TipoVeiculo	1
Valor	"R$ 104.933,00"
Marca	"VW - VolksWagen"
Modelo	"AMAROK High.CD 2.0 16V TDI 4x4 Dies. Aut"
AnoModelo	2014
Combustivel	"Diesel"
CodigoFipe	"005340-6"
MesReferencia	"maio de 2024"
SiglaCombustivel	"D"*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class TipoVeiculo {
    @JsonAlias("TipoVeiculo")
    private int idTipoVeiculo;
    @JsonAlias("Valor")
    private String valor;
    @JsonAlias("Marca")
    private String marca;
    @JsonAlias("Modelo")
    private String modelo;
    @JsonAlias("AnoModelo")
    private int anoModelo;
    @JsonAlias("Combustivel")
    private String combustivel;
    @JsonAlias("CodigoFipe")
    private String codigoFipe;
    @JsonAlias("MesReferencia")
    private String mesReferencia;
    @JsonAlias("SiglaCombustivel")
    private String siglaComb;

    public TipoVeiculo() {
    }

    public TipoVeiculo(int idTipoVeiculo, String combustivel, String codigoFipe, String mesReferencia, String siglaComb,
                       int anoModelo, String valor, ModeloCarro modelo, Marca marca, AnoCarro ano) {
        this.idTipoVeiculo = idTipoVeiculo;
        this.valor = valor;
        this.marca = marca.getNome();
        this.modelo = modelo.getModelo();
        this.anoModelo = anoModelo;
        this.combustivel = combustivel;
        this.codigoFipe = codigoFipe;
        this.mesReferencia = mesReferencia;
        this.siglaComb = siglaComb;
    }

    public int getIdTipoVeiculo() {
        return idTipoVeiculo;
    }

    public void setIdTipoVeiculo(int idTipoVeiculo) {
        this.idTipoVeiculo = idTipoVeiculo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(int anoModelo) {
        this.anoModelo = anoModelo;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public String getCodigoFipe() {
        return codigoFipe;
    }

    public void setCodigoFipe(String codigoFipe) {
        this.codigoFipe = codigoFipe;
    }

    public String getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public String getSiglaComb() {
        return siglaComb;
    }

    public void setSiglaComb(String siglaComb) {
        this.siglaComb = siglaComb;
    }

    @Override
    public String toString() {
        return "Valor: " + valor + "\n" + "Marca: " + marca + "\n" + "Modelo: " + modelo + "\n"
                + "Ano de Modelo: " + anoModelo + "\n" + "CodigoFipe: " + codigoFipe + "\n" + "MesReferencia: " + mesReferencia +
                "\n" + "SiglaComb: " + siglaComb;
    }
}