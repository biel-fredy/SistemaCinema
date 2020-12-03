package dominio.entidades;

import java.util.ArrayList;
import java.util.List;

import core.dao.anotacoes.Coluna;
import core.dao.anotacoes.Tabela;
import core.dao.anotacoes.UmPraMuitos;
import core.dao.anotacoes.enums.CascadeType;
import dominio.EntidadeDominio;
import dominio.entidadesenum.ClassificacaoIndicativa;

@Tabela(nomeTabela = "FILMES", trigama = "flm")
public class Filme extends EntidadeDominio{
	
	private String nome;
	private String diretor;
	
	@Coluna(value = "flm_class_ind")
	private ClassificacaoIndicativa classificacaoIndicativa;
	
	@Coluna(value = "flm_duracao")
	private Integer tempoDuracao;
	
	@Coluna(value = "flm_cartaz")
	private Boolean emCartaz;
	
	private String uri;
	
	@UmPraMuitos(cascade = CascadeType.ALL, mappedBy = "flm_id")
	private List<Sessao> sessaos = new ArrayList<Sessao>();
	
	public Filme() {
		super();
	}
	
	public Filme(Boolean emCartaz) {
		super();
		this.emCartaz = emCartaz;
	}
	
	public Filme(String nome, String diretor, ClassificacaoIndicativa classificacaoIndicativa, Integer tempoDuracao,
			Boolean emCartaz) {
		super();
		this.nome = nome;
		this.diretor = diretor;
		this.classificacaoIndicativa = classificacaoIndicativa;
		this.tempoDuracao = tempoDuracao;
		this.emCartaz = emCartaz;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nomeFilme) {
		this.nome = nomeFilme;
	}
	public String getDiretor() {
		return diretor;
	}
	public void setDiretor(String nomeDiretor) {
		this.diretor = nomeDiretor;
	}
	public ClassificacaoIndicativa getClassificacaoIndicativa() {
		return classificacaoIndicativa;
	}
	public void setClassificacaoIndicativa(ClassificacaoIndicativa classificacaoIndicativa) {
		this.classificacaoIndicativa = classificacaoIndicativa;
	}
	public Integer getTempoDuracao() {
		return tempoDuracao;
	}
	public void setTempoDuracao(Integer tempoDuracao) {
		this.tempoDuracao = tempoDuracao;
	}
	public Boolean getEmCartaz() {
		return emCartaz;
	}
	public void setEmCartaz(Boolean emCartaz) {
		this.emCartaz = emCartaz;
	}
	public List<Sessao> getSessaos() {
		return sessaos;
	}
	public void setSessaos(List<Sessao> sessaos) {
		this.sessaos = sessaos;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@Override
	public String toString() {
		return "Filme [nome=" + nome + ", diretor=" + diretor + ", classificacaoIndicativa=" + classificacaoIndicativa
				+ ", tempoDuracao=" + tempoDuracao + ", emCartaz=" + emCartaz + ", uri=" + uri + "]";
	}	
}
