package dominio.entidades;

import core.dao.anotacoes.Coluna;
import core.dao.anotacoes.MuitosPraUm;
import core.dao.anotacoes.Tabela;
import core.dao.anotacoes.enums.CascadeType;
import dominio.EntidadeDominio;

@Tabela(nomeTabela = "ASSENTOS", trigama = "ast")
public class Assento extends EntidadeDominio{
	
	@MuitosPraUm(cascade = CascadeType.ALL, mappedBy = "sal_id", removerOrfao = false)
	private SalaExibicao sala;
	
	@Coluna(value = "ast_numero")
	private Integer numeroAssento;
	
	@MuitosPraUm(cascade = CascadeType.ALL, mappedBy = "ast_fileira", removerOrfao = false)
	private Fileira fileira;
	
	public Assento() {
		super();
	}
	
	public Integer getNumeroAssento() {
		return numeroAssento;
	}
	public void setNumeroAssento(Integer numeroAssento) {
		this.numeroAssento = numeroAssento;
	}
	public Fileira getFileira() {
		return fileira;
	}
	public void setFileira(Fileira fileira) {
		this.fileira = fileira;
	}
	public SalaExibicao getSala() {
		return sala;
	}
	public void setSala(SalaExibicao sala) {
		this.sala = sala;
	}
	
}
