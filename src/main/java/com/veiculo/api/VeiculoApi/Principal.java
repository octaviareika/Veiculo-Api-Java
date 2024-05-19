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


public class Principal {
    private Scanner scanner = new Scanner(System.in);
    public Modelo modelos;
    public  converteDadosMapper conversao = new converteDadosMapper();
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
            System.out.println("Carro");
            System.out.println("Moto");
            System.out.println("Caminhao");
            opcao = scanner.nextLine();

            if (opcao.equals("Caminhao")){
                // algo aqui
            }
            else if (opcao.equals("Moto")){
                // algo aqui
            }

            else if (opcao.equals("Carro")){
                // algo aqui
                obj = dado.obterDadosCarro("https://parallelum.com.br/fipe/api/v1/carros/marcas");
               // System.out.println(obj);
                List<Marca> marcaCarro = conversao.converteDeJsonParaObjeto(obj, new TypeReference<List<Marca>>(){});
                
                System.out.println("Digite o codigo da marca do carro");
                String codigo = scanner.nextLine();
                obj = dado.obterDadosMarca(codigo);

                Modelo modelos = conversao.converteDeJsonParaClasse(obj, Modelo.class);
                System.out.println(modelos);


                System.out.println("Digite o nome do Modelo");
                String nome = scanner.nextLine();
                Optional<ModeloCarro> modelo = modelos.modelos().stream()
                .filter(m -> m.getModelo().equalsIgnoreCase(nome)).findFirst();

                if (modelo.isPresent()){
                    System.out.println(modelo.get().getCodigo());
                }
                else {
                    System.out.println("Modelo não encontrado");
                }
              //  mostrarDados(nome);


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
}
