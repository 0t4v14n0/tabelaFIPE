package br.com.alura.tabelafipe.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumindoAPI {
	
	private static final String BASE_URL = "https://parallelum.com.br/fipe/api/v1/";
	
    public String busca(String veiculo, String marca, String modelo, String ano) throws IOException, InterruptedException { 
        
        String endereco = "";
        
        //                    https://parallelum.com.br/fipe/api/v1/carros/marcas/59/modelos/2362/anos/2001-1
        
        if (marca != "") {
        	        	
        	if(modelo != "") {	
        		
        		if(ano != "") {
        			
        			endereco = BASE_URL+veiculo+"/marcas/"+marca+"/modelos/"+modelo+"/anos/"+ano;
        		}else {
        			
            		endereco = BASE_URL+veiculo+"/marcas/"+marca+"/modelos/"+modelo+"/anos";
        		}
       		
        	}else {
        		endereco = BASE_URL+veiculo+"/marcas/"+marca;
        		
        	}
        	
        }else {
        	endereco = BASE_URL+veiculo+"/marcas";
        }

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());   
                        
        String retorno = response.body();
        
        //System.out.println(retorno);

        return retorno;
        
    }
}
