package com.veiculo.api.VeiculoApi.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.veiculo.api.VeiculoApi.Marca;

import com.veiculo.api.VeiculoApi.TipoVeiculo.TipoVeiculo;

public interface RepositorioVeiculo extends JpaRepository<TipoVeiculo, Long>{

    public TipoVeiculo findByMarca(Marca marca);

    public TipoVeiculo findByModelo(String modelo);

    public TipoVeiculo findByAnoModelo(int anoModelo);

    public List<TipoVeiculo> findByAnoModeloBetween(int anoModelo1, int anoModelo2);

    //metodo personalizado, procurar o modelo do veiculo por meio do nome da marca
    
    //metodo que acha os três veículos mais baratos
    @Query("SELECT v from TipoVeiculo v ORDER BY v.valor DESC")
    public List<TipoVeiculo> procurarVeiculoMaisBarato(PageRequest pageRequest);

    //procurar veículo mais recentes
    @Query("SELECT v from TipoVeiculo v ORDER BY v.anoModelo DESC")
    public List<TipoVeiculo> procurarVeiculoMaisRecente(PageRequest pageRequest);


}
