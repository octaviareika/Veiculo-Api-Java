package com.veiculo.api.VeiculoApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.veiculo.api.VeiculoApi.Marca;

import com.veiculo.api.VeiculoApi.ModeloCarro;
import com.veiculo.api.VeiculoApi.TipoVeiculo.TipoVeiculo;

public interface RepositorioVeiculo extends JpaRepository<TipoVeiculo, Long>{

    public TipoVeiculo findByMarca(Marca marca);

    public TipoVeiculo findByModelo(String modelo);

    public TipoVeiculo findByAnoModelo(int anoModelo);

    public List<TipoVeiculo> findByAnoModeloBetween(int anoModelo1, int anoModelo2);

    //metodo personalizado
    @Query("SELECT v from TipoVeiculo v WHERE v.modelo = :modelo")
    public List<TipoVeiculo> procurarVeiculoPorModelo(ModeloCarro modelo);


}
