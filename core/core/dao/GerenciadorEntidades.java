package core.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dominio.EntidadeDominio;

public class GerenciadorEntidades {
	
	static public HashMap<String, List<EntidadeDominio>> entidadesMonitoradas;
	
	static public void addEntidadeMonitorada(String nomeClasse, EntidadeDominio entidade) {
		boolean encontrou = false;
		for (String nomeEntidade : entidadesMonitoradas.keySet()) {
			if (nomeClasse.equals(nomeEntidade)){
				entidadesMonitoradas.get(nomeEntidade).add(entidade);
				encontrou = true;
			}
		}
		
		if (encontrou==false) {
			List<EntidadeDominio> novaLista = new ArrayList<EntidadeDominio>();
			novaLista.add(entidade);
			entidadesMonitoradas.put(nomeClasse, novaLista);
		}
	}

}
