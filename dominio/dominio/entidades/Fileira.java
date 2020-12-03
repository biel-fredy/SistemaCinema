package dominio.entidades;

import java.util.ArrayList;
import java.util.List;

import dominio.EntidadeDominio;

public class Fileira extends EntidadeDominio{
	
	private Character identificacao;
	
	private SalaExibicao sala;	
	private List<Assento> listaAssentos = new ArrayList<Assento>();
	
	public Fileira() {
		super();
	}

	public Character getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(Character identificacao) {
		this.identificacao = identificacao;
	}

	public List<Assento> getListaAssentos() {
		return listaAssentos;
	}

	public void setListaAssentos(List<Assento> listaAssentos) {
		this.listaAssentos = listaAssentos;
	}

	public SalaExibicao getSala() {
		return sala;
	}

	public void setSala(SalaExibicao sala) {
		this.sala = sala;
	}

}
