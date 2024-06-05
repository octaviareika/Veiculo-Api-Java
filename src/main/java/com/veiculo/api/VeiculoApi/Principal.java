package com.veiculo.api.VeiculoApi;

//import org.json.JSONObject;
//import org.springframework.aot.hint.TypeReference;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
////import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.veiculo.api.VeiculoApi.TipoVeiculo.TipoVeiculo;
import com.veiculo.api.VeiculoApi.repository.RepositorioVeiculo;
import com.veiculo.api.VeiculoApi.repository.RepositoryMarca;

import org.hibernate.internal.util.collections.ConcurrentReferenceHashMap.Option;
import org.springframework.boot.Banner;
import org.springframework.data.domain.PageRequest;


public class Principal {
    private Scanner scanner = new Scanner(System.in);
    public Modelo modelos;
    public  converteDadosMapper conversao = new converteDadosMapper();
    public List<TipoVeiculo> veiculosGuardados = new ArrayList<>();
    public List<TipoVeiculo> veiculosGuardadosNoBanco = new ArrayList<>();
    public List<Marca> marcaSalvaNoBanco = new ArrayList<>();
    private RepositoryMarca repositoryMarca;

    private RepositorioVeiculo  repositorioVeiculo;
    public Principal(RepositorioVeiculo repositorioVeiculo, RepositoryMarca repositoryMarca) {
        this.modelos = new Modelo(new ArrayList<>());
        this.repositorioVeiculo = repositorioVeiculo;
        this.repositoryMarca = repositoryMarca;
        
        
    }

    
    public void exibeMenu() throws IOException, InterruptedException {
        boolean tentativa = true;
        String opcao;
        


        ConversaoDado dado = new ConversaoDado();
        String obj;
        while(tentativa){
            System.out.println("Digite uma opçao para olhar");
            System.out.println("-> Carros");
            
            System.out.println("-> Caminhoes");
            System.out.println("-> Mostrar veiculos armazenados (Digite 3)");
            System.out.println("-> Procurar veículos armazaneados por meio da marca (Digite 4)");
            System.out.println("-> Procurar veículos armazenados por meio de um intervalo de ano (Digite 5)");
            System.out.println("-> Mostrar os três veículos mais baratos (Digite 6)");
            System.out.println("-> Sair");
            opcao = scanner.nextLine();

            if (opcao.toLowerCase().equals("caminhoes")) {
                // algo aqui
                obj = dado.obterDadosCarro("https://parallelum.com.br/fipe/api/v1/caminhoes/marcas");
                List<Marca> marcaCaminhao = conversao.converteDeJsonParaObjeto(obj, new TypeReference<List<Marca>>() {
                }); // converte para array

                System.out.println(marcaCaminhao);
                System.out.println("Digite o nome da marca do caminhao");

                String nomeMarca = scanner.nextLine();
                Optional<Marca> marcaEncontrada = marcaCaminhao.stream()
                        .filter(caminhao -> caminhao.getNome().equalsIgnoreCase(nomeMarca)).findFirst();

                
                if (marcaEncontrada.isPresent()) {
                    // salvar no banco
                    armazenarMarcaESeusDadosNoBanco(marcaEncontrada.get());

                    obj = dado.obterDadosMarca(opcao.toLowerCase(), marcaEncontrada.get().getCodigo());
                    Modelo modelos = conversao.converteDeJsonParaClasse(obj, Modelo.class);
                
                    System.out.println(modelos);
                    System.out.println("Digite o nome da modelo do caminhao");
                    var nomeModelo = scanner.nextLine();

                    Optional<ModeloCarro> modeloEncontrado = modelos.modelos().stream()
                            .filter(m -> m.getModelo().equalsIgnoreCase(nomeModelo)).findFirst();


                    if (modeloEncontrado.isPresent()) {
                        obj = dado.obterDadosModelo(opcao.toLowerCase(), marcaEncontrada.get().getCodigo(), modeloEncontrado.get().getCodigo());
                        //System.out.println(obj);
                       List<AnoCarro> anos = conversao.converteDeJsonParaObjeto(obj, new TypeReference<List<AnoCarro>>() {});
                       System.out.println(anos);
                       System.out.println("Digite o nome do ano do modelo do caminhao");
                       var anoModelo = scanner.nextLine();

                       Optional<AnoCarro> anoEncontrado = anos.stream()
                               .filter(a -> a.getNome().toLowerCase().equalsIgnoreCase(anoModelo.toLowerCase())).findFirst();
                       if (anoEncontrado.isPresent()) {
                           obj = dado.obterDadosFinal(opcao.toLowerCase(), marcaEncontrada.get().getCodigo(),
                                   anoEncontrado.get().getCodigo(), modeloEncontrado.get().getCodigo());
                           TipoVeiculo veiculoFinal = conversao.converteDeJsonParaClasse(obj, TipoVeiculo.class);
                           System.out.println(veiculoFinal.toString());
                           System.out.println("Deseja armazenar esse veiculo? (s/n)");
                            var armazenar = scanner.nextLine();
                            if (armazenar.equalsIgnoreCase("s")){
                               
                                salvarVeiculoNoBanco(veiculoFinal);
                                System.out.println("Veiculo armazenado com sucesso!");

                            }
                            else {
                                System.out.println("Veiculo nao armazenado");
                                
                            }
                       }
                    }


                } else {
                    System.out.println("Marca nao encontrada");
                }
            }

            else if (opcao.toLowerCase().equals("carros")){
                // algo aqui - feito
                obj = dado.obterDadosCarro("https://parallelum.com.br/fipe/api/v1/carros/marcas");
               
                List<Marca> marcaCarro = conversao.converteDeJsonParaObjeto(obj, new TypeReference<List<Marca>>(){});
                
                System.out.println("Digite o codigo da marca do carro");
                String codigo = scanner.nextLine();
                obj = dado.obterDadosMarca(opcao.toLowerCase(), codigo);
                // salvar o nome da marca no banco
                Marca marca = conversao.converteDeJsonParaClasse(obj, Marca.class); // converte para objeto
                armazenarMarcaESeusDadosNoBanco(marca);
                
                Modelo modelos = conversao.converteDeJsonParaClasse(obj, Modelo.class);
                System.out.println(modelos);


                System.out.println("Digite o nome do Modelo");
                String nome = scanner.nextLine();
                Optional<ModeloCarro> modelo = modelos.modelos().stream()
                .filter(m -> m.getModelo().equalsIgnoreCase(nome)).findFirst();

                if (modelo.isPresent()){
                    
                    obj = dado.obterDadosModelo(opcao.toLowerCase(), codigo, modelo.get().getCodigo());
                    
                    List<AnoCarro> anoCarro = conversao.converteDeJsonParaObjeto(obj, new TypeReference<List<AnoCarro>>(){});
                    System.out.println(anoCarro);
                    System.out.println("Digite o nome do ano do Modelo");
                    String ano = scanner.nextLine();

                    Optional<AnoCarro> anoEncontrado = anoCarro.stream()
                            .filter(a -> a.getNome().equalsIgnoreCase(ano)).findFirst();
                    if (anoEncontrado.isPresent()){
                        obj = dado.obterDadosFinal(opcao.toLowerCase(), codigo, anoEncontrado.get().getCodigo(), modelo.get().getCodigo());
                        
                        TipoVeiculo dadoFinal = conversao.converteDeJsonParaClasse(obj, TipoVeiculo.class);
                        System.out.println(dadoFinal);
                        System.out.println("Deseja armazenar esse veiculo? (s/n)");
                        String armazenar = scanner.nextLine();
                        if (armazenar.equalsIgnoreCase("s")){
                            System.out.println("Armazenando...");
                            salvarVeiculoNoBanco(dadoFinal);
                            System.out.println("Veiculo armazenado com sucesso!");
                        }
                        else {
                            System.out.println("Finalizando");
                        }
                        
                    }
                    else {
                        System.out.println("Nao existe esse carro");
                        tentativa = false;
                    }


                }
                else {
                    System.out.println("Modelo não encontrado");
                    tentativa = false;
                }


            }

            else if (opcao.toLowerCase().equals("3")){
                mostrarVeiculosSalvosNoBanco();
            }

            else if (opcao.toLowerCase().equals("sair")){
                tentativa = false;
            }

            else if (opcao.equals("4")){
                procurarVeiculoPorMarca();
            }

            else if (opcao.equals("5")){
               //mostrar veiculos armazenados num intervalo de ano
                System.out.println("Digite o ano inicial");
                int anoInicial = scanner.nextInt();
                System.out.println("Digite o ano final");
                int anoFinal = scanner.nextInt();
                mostrarVeiculosPorIntervaloDeAno(anoInicial, anoFinal);
            }


            else if (opcao.equals("6")){
                mostrarOs3VeiculosMaisBaratos();
            }


            else {
                System.out.println("Opção inválida");
            }

        }
    }



    public void mostrarDados(String nome) throws IOException, InterruptedException {;
        Optional<ModeloCarro> modelo = modelos.modelos().stream()
        .filter(m -> m.getModelo().equalsIgnoreCase(nome)).findFirst();

        if (modelo.isPresent()){
            System.out.println(modelo.get().getCodigo());
        }
        else {
            System.out.println("Modelo não encontrado");
        }
    }

    public void armazenaVeiculo(TipoVeiculo veiculo) {
         veiculosGuardados.add(veiculo);
        
    }

    public void mostrarVeiculosSalvosNoBanco(){
        veiculosGuardadosNoBanco = repositorioVeiculo.findAll();
        veiculosGuardadosNoBanco.stream()
            .sorted((v1, v2) -> v1.getMarca().compareTo(v2.getMarca()))
            .forEach(System.out::println);
        
    }

    public void salvarVeiculoNoBanco(TipoVeiculo veiculo){

        repositorioVeiculo.save(veiculo);
    }

    public void procurarVeiculoPorMarca(){
        System.out.println("Procurando veiculo por marca");
        System.out.println("Digite a marca do veiculo: ");
        String marcaBuscada = scanner.nextLine();
        veiculosGuardadosNoBanco = repositorioVeiculo.findAll();
        // e se tiver vários veiculos com a mesma marca?
        
        List<TipoVeiculo> veiculos = veiculosGuardadosNoBanco.stream()
            .filter(v -> v.getMarca().equalsIgnoreCase(marcaBuscada))
            .toList();

        if (veiculos.isEmpty()){
            System.out.println("Nenhum veiculo encontrado");
        }
        else {
            veiculos.forEach(System.out::println);
        }

        
    }

    public void armazenarMarcaESeusDadosNoBanco(Marca marca){
        repositoryMarca.save(marca);

    }

    public void mostrarMarcasSalvasNoBanco(){
        marcaSalvaNoBanco = repositoryMarca.findAll();
        marcaSalvaNoBanco.stream()
            .sorted((m1, m2) -> m1.getNome().compareTo(m2.getNome()))
            .forEach(System.out::println);
    }

    private void mostrarVeiculosPorIntervaloDeAno(int anoInicial, int anoFinal) {
        veiculosGuardadosNoBanco = repositorioVeiculo.findAll();
       //    public List<TipoVeiculo> findByAnoModeloBetween(int anoModelo1, int anoModelo2);
        List<TipoVeiculo> veiculoIntervaloDeTempo = repositorioVeiculo.findByAnoModeloBetween(anoInicial, anoFinal);

        if (veiculoIntervaloDeTempo.isEmpty()){
            System.out.println("Nenhum veiculo encontrado");
        }
        else {
            veiculoIntervaloDeTempo.forEach(System.out::println);
        }
    }



    public void mostrarOs3VeiculosMaisBaratos(){
        veiculosGuardadosNoBanco = repositorioVeiculo.findAll();
        // @Query("SELECT v from TipoVeiculo v ORDER BY v.preco ASC")
        List<TipoVeiculo> veiculosMaisBaratos = repositorioVeiculo.procurarVeiculoMaisBarato(PageRequest.of(0, 3));
        if (veiculosMaisBaratos.isEmpty()){
            System.out.println("Nenhum veiculo encontrado");
        }
        else {
            veiculosMaisBaratos.forEach(System.out::println);
        }
    }

}
