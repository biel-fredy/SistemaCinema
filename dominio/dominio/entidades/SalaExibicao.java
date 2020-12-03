package dominio.entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import core.dao.anotacoes.Coluna;
import core.dao.anotacoes.NaoGravar;
import core.dao.anotacoes.Tabela;
import core.dao.anotacoes.UmPraMuitos;
import core.dao.anotacoes.enums.CascadeType;
import dominio.EntidadeDominio;
import dominio.entidadesenum.TipoSala;

@Tabela(nomeTabela = "SALAS", trigama = "sal")
public class SalaExibicao extends EntidadeDominio{
	
	private String nome;
	
	@Coluna(value = "sal_tipo")
	private TipoSala tipoSala;
	
	@Coluna(value = "sal_n_extintores")
	private Integer numeroExtintores;
	
	@UmPraMuitos(cascade = CascadeType.ALL, mappedBy = "sal_id")
	private List<Sessao> listaSessao = new ArrayList<Sessao>();
	
	@NaoGravar
	private HashMap<Character, Assento> listaAssentos = new HashMap<Character, Assento>();
	
	public SalaExibicao() {
		super();
	}
	
	public SalaExibicao(Integer numeroExtintores, TipoSala tipoSala, String nome) {
		this.numeroExtintores = numeroExtintores;
		this.tipoSala = tipoSala;
		this.nome = nome;
	}
	
	public Integer getNumeroExtintores() {
		return numeroExtintores;
	}
	public void setNumeroExtintores(Integer numeroExtintores) {
		this.numeroExtintores = numeroExtintores;
	}
	public TipoSala getTipoSala() {
		return tipoSala;
	}
	public void setTipoSala(TipoSala tipoSala) {
		this.tipoSala = tipoSala;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Sessao> getListaSessao() {
		return this.listaSessao;
	}
	public void setListaSessao(List<Sessao> listaSessao) {
		this.listaSessao = listaSessao;
	}
	public HashMap<Character, Assento> getListaAssentos() {
		return listaAssentos;
	}
	public void setListaAssentos(HashMap<Character, Assento> listaAssentos) {
		this.listaAssentos = listaAssentos;
	}
	
	public void addSessao(Sessao sessao) {
		this.listaSessao.add(sessao);
	}
	
	public void removeSessao(Sessao sessao) {
		this.listaSessao.remove(sessao);
	}
		
	public int sizeListaAnexos() {
		return this.listaSessao.size();
	}	

}
