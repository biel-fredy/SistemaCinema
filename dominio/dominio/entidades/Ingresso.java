package dominio.entidades;

import core.dao.anotacoes.Coluna;
import core.dao.anotacoes.MuitosPraUm;
import core.dao.anotacoes.Tabela;
import core.dao.anotacoes.enums.CascadeType;
import dominio.EntidadeDominio;
import dominio.entidadesenum.TipoIngresso;

@Tabela(nomeTabela = "INGRESSOS", trigama = "ing")
public class Ingresso extends EntidadeDominio {
	
	@MuitosPraUm(cascade = CascadeType.ALL, mappedBy = "ses_id", removerOrfao = false)
	private Sessao sessao;
	
	@MuitosPraUm(cascade = CascadeType.ALL, mappedBy = "ast_id", removerOrfao = false)
	private Assento assento;
	
	@MuitosPraUm(cascade = CascadeType.ALL, mappedBy = "des_id", removerOrfao = false)
	private Desconto desconto;
	
	@MuitosPraUm(cascade = CascadeType.ALL, mappedBy = "cmp_id", removerOrfao = false)
	private Compra compra;
	
	private Double preco;
	
	@Coluna(value = "ing_tipo")
	private TipoIngresso tipoIngresso;
	
	public Ingresso() {
		super();
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public Assento getAssento() {
		return assento;
	}

	public void setAssento(Assento assento) {
		this.assento = assento;
	}

	public Desconto getDesconto() {
		return desconto;
	}

	public void setDesconto(Desconto desconto) {
		this.desconto = desconto;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public TipoIngresso getTipoIngresso() {
		return tipoIngresso;
	}

	public void setTipoIngresso(TipoIngresso tipoIngresso) {
		this.tipoIngresso = tipoIngresso;
	}

}
