package dominio.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import core.dao.anotacoes.Coluna;
import core.dao.anotacoes.Tabela;
import core.dao.anotacoes.UmPraMuitos;
import core.dao.anotacoes.enums.CascadeType;
import dominio.EntidadeDominio;

@Tabela(nomeTabela = "DESCONTOS", trigama = "des")
public class Desconto extends EntidadeDominio {
	
	private Double porcentagem;
	private String descricao;
	
	@Coluna(value = "des_data_limite")
	private Date dataLimite;
	
	@UmPraMuitos(cascade = CascadeType.ALL, mappedBy = "des_id")
	private List<Ingresso> ingressos = new ArrayList<Ingresso>();
	
	public Desconto() {
		super();
	}

	public Double getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(Double porcentagem) {
		this.porcentagem = porcentagem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataLimite() {
		return dataLimite;
	}

	public void setDataLimite(Date dataLimite) {
		this.dataLimite = dataLimite;
	}

	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}
	
	

}
