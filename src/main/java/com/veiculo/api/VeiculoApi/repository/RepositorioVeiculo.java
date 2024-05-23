package com.veiculo.api.VeiculoApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veiculo.api.VeiculoApi.Marca;
import com.veiculo.api.VeiculoApi.TipoVeiculo.TipoVeiculo;

public interface RepositorioVeiculo extends JpaRepository<TipoVeiculo, Long>{

    public TipoVeiculo findByMarca(Marca marca);

    
   



}
