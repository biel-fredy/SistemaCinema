package dominio.entidades;

import java.util.ArrayList;
import java.util.List;

import core.dao.anotacoes.MuitosPraUm;
import core.dao.anotacoes.Tabela;
import core.dao.anotacoes.UmPraMuitos;
import core.dao.anotacoes.enums.CascadeType;
import dominio.EntidadeDominio;

@Tabela(nomeTabela = "COMPRAS", trigama = "cmp")
public class Compra extends EntidadeDominio {
	
	@MuitosPraUm(cascade = CascadeType.ALL, mappedBy = "cli_id", removerOrfao = false)
	private Cliente cliente;
	
	@UmPraMuitos(cascade = CascadeType.ALL, mappedBy = "cmp_id")
	private List<Ingresso> listaIngressos = new ArrayList<Ingresso>();
	
	public Compra() {
		super();
	}
	
	public List<Ingresso> getListaIngressos() {
		return listaIngressos;
	}

	public void setListaIngressos(List<Ingresso> listaIngressos) {
		this.listaIngressos = listaIngressos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
