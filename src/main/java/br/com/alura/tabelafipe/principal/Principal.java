package br.com.alura.tabelafipe.principal;

import java.io.IOException;

import br.com.alura.tabelafipe.service.ConsumindoAPI;

public class Principal {
	
	public void menu() {
		
		ConsumindoAPI api = new ConsumindoAPI();
		
        //https://parallelum.com.br/fipe/api/v1/carros/marcas/59/modelos/2362/anos/2001-1
		String retorno = "";

		
		try {
			retorno = api.busca("carros", "6", "43", "1993-1");
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(retorno);
		
	}

}
