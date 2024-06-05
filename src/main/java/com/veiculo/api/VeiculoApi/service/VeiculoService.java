package com.veiculo.api.VeiculoApi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.DTO.TipoVeiculoDTO;
import com.veiculo.api.VeiculoApi.TipoVeiculo.TipoVeiculo;
import com.veiculo.api.VeiculoApi.repository.RepositorioVeiculo;

@Service
public class VeiculoService {
    @Autowired

    private RepositorioVeiculo repositorioVeiculo;


    public List<TipoVeiculoDTO> obterVeiculos(){
        return repositorioVeiculo.findAll()
                .stream()
                .map(v -> new TipoVeiculoDTO(
                    v.getId(),
                    v.getIdTipoVeiculo(),
                    v.getValor(),
                    v.getMarca(),
                    v.getModelo(),
                    v.getAnoModelo(),
                    v.getCombustivel(),
                    v.getCodigoFipe(),
                    v.getMesReferencia(),
                    v.getSiglaComb()
                ))
                .toList();
    }


    public List<TipoVeiculoDTO> obterVeiculosEconomicos() {
        return repositorioVeiculo.procurarVeiculoMaisBarato(PageRequest.of(0, 3))
                .stream()
                .map(v -> new TipoVeiculoDTO(
                    v.getId(),
                    v.getIdTipoVeiculo(),
                    v.getValor(),
                    v.getMarca(),
                    v.getModelo(),
                    v.getAnoModelo(),
                    v.getCombustivel(),
                    v.getCodigoFipe(),
                    v.getMesReferencia(),
                    v.getSiglaComb()
                ))
                .toList();
    }


    public List<TipoVeiculoDTO> obterVeiculosRecentes() {
       
        return repositorioVeiculo.procurarVeiculoMaisRecente(PageRequest.of(0, 3))
                .stream()
                .map(v -> new TipoVeiculoDTO(
                    v.getId(),
                    v.getIdTipoVeiculo(),
                    v.getValor(),
                    v.getMarca(),
                    v.getModelo(),
                    v.getAnoModelo(),
                    v.getCombustivel(),
                    v.getCodigoFipe(),
                    v.getMesReferencia(),
                    v.getSiglaComb()
                ))
                .toList();
    }
}
