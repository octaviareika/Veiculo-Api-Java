package com.veiculo.api.VeiculoApi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DTO.TipoVeiculoDTO;

import com.veiculo.api.VeiculoApi.service.VeiculoService;


@RestController // indica que a classe é um controlador para a API
public class TipoVeiculoController {
    
    @Autowired
    private VeiculoService veiculoService;

    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @GetMapping("/veiculos") // indica que o método será chamado quando a requisição for feita para a URL /veiculos
    public List<TipoVeiculoDTO> listarVeiculos() {
        return veiculoService.obterVeiculos();
    }

    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @GetMapping("/veiculos/economicos")
    public List<TipoVeiculoDTO> listarVeiculosBaratos() {
        return veiculoService.obterVeiculosEconomicos();
    }

    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @GetMapping("/veiculos/recentes")
    public List<TipoVeiculoDTO> listarVeiculosRecentes() {
        return veiculoService.obterVeiculosRecentes();
    }
}

