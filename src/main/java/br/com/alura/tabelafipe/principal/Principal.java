package br.com.alura.tabelafipe.principal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.alura.tabelafipe.model.Dados;
import br.com.alura.tabelafipe.model.Modelos;
import br.com.alura.tabelafipe.model.Veiculo;
import br.com.alura.tabelafipe.service.ConsumindoAPI;
import br.com.alura.tabelafipe.service.ConverteDados;

public class Principal {
	
	private Scanner leitura = new Scanner(System.in);
	
	private ConsumindoAPI api = new ConsumindoAPI();
	
	private ConverteDados conversor = new ConverteDados();
	
	public void menu() {
						
        System.out.println("O que quer buscar ? \n"
        		         + " - carros \n"
        		         + " - motos  \n"
        		         + " - caminhoes  \n");
        
        var veiculo = leitura.nextLine();
        
        // lista das marcas
        
        System.out.println("");
        System.out.println("Lista de Marcas: ");
        System.out.println("");
		String json = retornaConsulta(veiculo, "", "", "");
		
		var dadosmodelo = conversor.obterLista(json, Dados.class);
		
		dadosmodelo.stream()
			.sorted (Comparator.comparing (Dados::codigo))
			.forEach(System.out::println);
		
		// lista de modelos
		
        System.out.println("\n Informe o codigo da marca : ");

        var marca = leitura.nextLine();
        
        json = retornaConsulta(veiculo, marca, "", "");
        
        var modeloLista = conversor.obterDados(json, Modelos.class);
        
        System.out.println("\n Modelos dessa marca: ");
        modeloLista.modelos().stream()
        		.sorted(Comparator.comparing(Dados::codigo))
        		.forEach(System.out::println);
        
        //busca especifica
        System.out.println("\n Informe o nome do carro : ");

        var nomeVeiculo = leitura.nextLine();
        
        List<Dados> modelosFiltrados = modeloLista.modelos().stream()
        	    .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
        	        .collect(Collectors.toList());
        	        
        System.out.println("\nModelos filtrados");
        modelosFiltrados.forEach(System.out::println);
        
        //lista os precos e ano
        
        System.out.println("\n Informe o codigo do veiculo : ");

        var codVeiculo = leitura.nextLine();
        
        json = retornaConsulta(veiculo, marca, codVeiculo, "");
        
        List<Dados> anos = conversor.obterLista(json, Dados.class);
        List<Veiculo> veiculos = new ArrayList<>();

        for (int i = 0; i < anos.size(); i++) {
        	var enderecoAnos = anos.get(i).codigo();
            json = retornaConsulta(veiculo, marca, codVeiculo, enderecoAnos);
            Veiculo veiculoInstancia = conversor.obterDados(json, Veiculo.class);
            veiculos.add(veiculoInstancia);
        }
        
        System.out.println("\nTodos os veiculos filtrados com avaliações por ano: "); 
        veiculos.forEach(System.out::println);

	}
	
    public String retornaConsulta(String veiculo,String marca,String modelo, String ano) {
    	
    	String retorno = "";
    	
        try {
			retorno = api.busca(veiculo, marca, modelo, ano);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}   
		return retorno;	
    }

}
