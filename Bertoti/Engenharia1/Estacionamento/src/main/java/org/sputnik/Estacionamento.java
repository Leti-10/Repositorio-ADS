package org.sputnik;

import java.util.List;
import java.util.LinkedList;

public class Estacionamento {//classe sub letra maiu
	
	private List<Carro> carros = new LinkedList<Carro>(); //atrib sub minus
	
	public void addCarro(Carro carro) {
		carros.add(carro);
	}
	
	public Carro buscarCarroPlaca(String placa) {
		for(Carro carro:carros) {
			if(carro.getPlaca().equals(placa)) return carro;
		}
		return null;
	}
	
	public List<Carro> buscarCarroModelo(String modelo){
		List<Carro> encontrados = new LinkedList<Carro>();
		for(Carro carro: carros) {
			if(carro.getModelo().equals(modelo)) encontrados.add(carro);
		}
		return encontrados;
	}
	
	public List<Carro> getCarros(){//met verb minus
		return carros;
	}
}
