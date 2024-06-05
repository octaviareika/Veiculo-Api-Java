package com.DTO;


public record TipoVeiculoDTO(
    Long id,
    int idTipoVeiculo,
    String valor,
    String marca,
    String modelo,
    int anoModelo,
    String combustivel,
    String codigoFipe,
    String mesReferencia,
    String siglaComb
) {}