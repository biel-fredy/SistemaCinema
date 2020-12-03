package core.aplicacao;

import java.util.ArrayList;
import java.util.List;

import dominio.EntidadeDominio;

public class Resultado {
	
	private String mensagem;
	private List<EntidadeDominio> resultadoLista = new ArrayList<EntidadeDominio>();
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public List<EntidadeDominio> getResultadoLista() {
		return resultadoLista;
	}
	
	public void setResultadoLista(List<EntidadeDominio> resultadoLista) {
		this.resultadoLista = resultadoLista;
	}
	public void addEntidade(EntidadeDominio entidade) {
		this.resultadoLista.add(entidade);
	}
	
	public void removeEntidade(EntidadeDominio entidade) {
		this.resultadoLista.remove(entidade);
	}
		
	public int sizeListaAnexos() {
		return this.resultadoLista.size();
	}	

}
