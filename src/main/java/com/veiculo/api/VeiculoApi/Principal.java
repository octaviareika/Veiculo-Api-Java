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
import org.springframework.boot.Banner;


public class Principal {
    private Scanner scanner = new Scanner(System.in);
    public Modelo modelos;
    public  converteDadosMapper conversao = new converteDadosMapper();
    public List<TipoVeiculo> veiculosGuardados = new ArrayList<>();
    public Principal() {
        this.modelos = new Modelo(new ArrayList<>());

        
    }

    
    public void exibeMenu() throws IOException, InterruptedException {
        boolean tentativa = true;
        String opcao;
        //Marca marca;


        ConversaoDado dado = new ConversaoDado();
        String obj;
        while(tentativa){
            System.out.println("Digite uma opçao para olhar");
            System.out.println("-> Carros");
            //System.out.println("Moto");
            System.out.println("-> Caminhoes");
            System.out.println("-> Mostrar veiculos armazenados");
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
                               .filter(a -> a.getNome().equalsIgnoreCase(anoModelo)).findFirst();
                       if (anoEncontrado.isPresent()) {
                           obj = dado.obterDadosFinal(opcao.toLowerCase(), marcaEncontrada.get().getCodigo(),
                                   anoEncontrado.get().getCodigo(), modeloEncontrado.get().getCodigo());
                           TipoVeiculo veiculoFinal = conversao.converteDeJsonParaClasse(obj, TipoVeiculo.class);
                           System.out.println(veiculoFinal.toString());
                           System.out.println("Deseja armazenar esse veiculo? (s/n)");
                            var armazenar = scanner.nextLine();
                            if (armazenar.equalsIgnoreCase("s")){
                                armazenaVeiculo(veiculoFinal);
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
               // System.out.println(obj);
                List<Marca> marcaCarro = conversao.converteDeJsonParaObjeto(obj, new TypeReference<List<Marca>>(){});
                
                System.out.println("Digite o codigo da marca do carro");
                String codigo = scanner.nextLine();
                obj = dado.obterDadosMarca(opcao.toLowerCase(), codigo);

                Modelo modelos = conversao.converteDeJsonParaClasse(obj, Modelo.class);
                System.out.println(modelos);


                System.out.println("Digite o nome do Modelo");
                String nome = scanner.nextLine();
                Optional<ModeloCarro> modelo = modelos.modelos().stream()
                .filter(m -> m.getModelo().equalsIgnoreCase(nome)).findFirst();

                if (modelo.isPresent()){
                    //System.out.println(modelo.get().getCodigo());
                    obj = dado.obterDadosModelo(opcao.toLowerCase(), codigo, modelo.get().getCodigo());
                    //System.out.println(obj);
                    List<AnoCarro> anoCarro = conversao.converteDeJsonParaObjeto(obj, new TypeReference<List<AnoCarro>>(){});
                    System.out.println(anoCarro);
                    System.out.println("Digite o nome do ano do Modelo");
                    String ano = scanner.nextLine();

                    Optional<AnoCarro> anoEncontrado = anoCarro.stream()
                            .filter(a -> a.getNome().equalsIgnoreCase(ano)).findFirst();
                    if (anoEncontrado.isPresent()){
                        obj = dado.obterDadosFinal(opcao.toLowerCase(), codigo, anoEncontrado.get().getCodigo(), modelo.get().getCodigo());
                        //System.out.println(obj);
                        TipoVeiculo dadoFinal = conversao.converteDeJsonParaClasse(obj, TipoVeiculo.class);
                        System.out.println(dadoFinal);
                        System.out.println("Deseja armazenar esse veiculo? (s/n)");
                        String armazenar = scanner.nextLine();
                        if (armazenar.equalsIgnoreCase("s")){
                            armazenaVeiculo(dadoFinal);
                            System.out.println("Armazenando...");
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

            else if (opcao.toLowerCase().equals("mostrar veiculos armazenados")){
                mostrarVeiculosArmazenados();
            }

            else if (opcao.toLowerCase().equals("sair")){
                tentativa = false;
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

    public void mostrarVeiculosArmazenados(){
        veiculosGuardados.forEach(v -> System.out.println(v.toString() + "\n"));
    }

}
