package dominio.entidades;

import java.util.Date;

import core.dao.anotacoes.Coluna;
import core.dao.anotacoes.MuitosPraUm;
import core.dao.anotacoes.Tabela;
import core.dao.anotacoes.enums.CascadeType;
import dominio.EntidadeDominio;
import dominio.entidadesenum.DiaDaSemana;
import dominio.entidadesenum.Periodo;

@Tabela(nomeTabela = "SESSOES", trigama = "ses")
public class Sessao extends EntidadeDominio {
	
	@MuitosPraUm(cascade = CascadeType.ALL, mappedBy = "flm_id", removerOrfao = false)
	private Filme filme;
	
	@MuitosPraUm(cascade = CascadeType.ALL, mappedBy = "sal_id", removerOrfao = false)
	private SalaExibicao salaExibicao;
	
	private Date horario;
	
	@Coluna(value = "ses_dia_semana")
	private DiaDaSemana diaDaSemana;
	
	private Periodo periodo;
	private Double preco;
	
	public Sessao() {
		super();
	}
	
	public Sessao(Filme filme, Date horario, DiaDaSemana diaDaSemana, Periodo periodo,
			Double preco) {
		super();
		this.filme = filme;
		this.horario = horario;
		this.diaDaSemana = diaDaSemana;
		this.periodo = periodo;
		this.preco = preco;
	}
	
	public Filme getFilme() {
		return filme;
	}
	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	public SalaExibicao getSalaExibicao() {
		return salaExibicao;
	}
	public void setSalaExibicao(SalaExibicao salaExibicao) {
		this.salaExibicao = salaExibicao;
	}
	public Date getHorario() {
		return horario;
	}
	public void setHorario(Date horario) {
		this.horario = horario;
	}
	public DiaDaSemana getDiaDaSemana() {
		return diaDaSemana;
	}
	public void setDiaDaSemana(DiaDaSemana diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}
	public Periodo getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}	
}
