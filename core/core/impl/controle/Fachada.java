package core.impl.controle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.IFachada;
import core.IStrategy;
import core.aplicacao.Resultado;
import core.dao.impl.GerenciadorDAOGenerico;
import core.impl.negocio.ComplementarDataCadastro;
import core.impl.negocio.ValidadorDadosObrigatoriosFilme;
import dominio.EntidadeDominio;
import dominio.entidades.Filme;

public class Fachada implements IFachada {
	
	private Map<String, Map<String, List<IStrategy>>> regrasDeNegocio;

	private Resultado resultado;
	
	public Fachada() {
		regrasDeNegocio = new HashMap<String, Map<String, List<IStrategy>>>();
		
		//Instanciar regras de negócio
		ComplementarDataCadastro comDataCadastro = new ComplementarDataCadastro();
		ValidadorDadosObrigatoriosFilme vDadosFilme = new ValidadorDadosObrigatoriosFilme();
		
		// Mapeando regras da Entidade Dominio: Filme

		// Lista Regras de Negocio - SALVAR
		List<IStrategy> rnsSalvarFilme = new ArrayList<IStrategy>();
		rnsSalvarFilme.add(comDataCadastro);
		rnsSalvarFilme.add(vDadosFilme);

		// Lista Regras de Negocio - ALTERAR
		List<IStrategy> rnsAlterarFilme = new ArrayList<IStrategy>();
		rnsSalvarFilme.add(vDadosFilme);

		// Lista Regras de Negocio - EXCLUIR

		// HashMap (Operacao, ListaRegrasNegocio)
		Map<String, List<IStrategy>> rnsFilme = new HashMap<String, List<IStrategy>>();
		rnsFilme.put("SALVAR", rnsSalvarFilme);
		rnsFilme.put("ALTERAR", rnsAlterarFilme);

		// Insere no HashMap das Regras de Negocio
		regrasDeNegocio.put(Filme.class.getName(), rnsFilme);

		// Fim Categoria
		// ---------------------------------------------------------------------------------------------------------------------
	}

	@Override
	public Resultado salvar(EntidadeDominio entidade) {
		resultado = new Resultado();

		String msg = executarRegras(entidade, "SALVAR");

		if (msg == null) {
			GerenciadorDAOGenerico gdao = new GerenciadorDAOGenerico();
			resultado = gdao.salvar(entidade);
		} else {
			resultado.setMensagem(msg);
		}

		return resultado;
	}

	@Override
	public Resultado alterar(EntidadeDominio entidade) {
		resultado = new Resultado();

		String msg = executarRegras(entidade, "SALVAR");

		if (msg == null) {
			GerenciadorDAOGenerico dao = new GerenciadorDAOGenerico();
			resultado = dao.alterar(entidade);
		} else {
			resultado.setMensagem(msg);
		}

		return resultado;
	}

	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		resultado = new Resultado();

		String msg = executarRegras(entidade, "SALVAR");

		if (msg == null) {
			GerenciadorDAOGenerico dao = new GerenciadorDAOGenerico();
			resultado = dao.excluir(entidade);
		} else {
			resultado.setMensagem(msg);
		}

		return resultado;
	}

	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		resultado = new Resultado();

		String msg = executarRegras(entidade, "SALVAR");

		if (msg == null) {
			GerenciadorDAOGenerico dao = new GerenciadorDAOGenerico();
			resultado = dao.consultar(entidade);
		} else {
			resultado.setMensagem(msg);
		}

		return resultado;
	}

	@Override
	public Resultado visualizar(EntidadeDominio entidade) {
		resultado = new Resultado();

		String msg = executarRegras(entidade, "SALVAR");

		if (msg == null) {
			GerenciadorDAOGenerico dao = new GerenciadorDAOGenerico();
			resultado = dao.consultar(entidade);
		} else {
			resultado.setMensagem(msg);
		}

		return resultado;
	}
	
	private String executarRegras(EntidadeDominio entidade, String operacao) {
		String nmClasse = entidade.getClass().getName();
		StringBuilder msg = new StringBuilder();

		Map<String, List<IStrategy>> regrasOperacao = regrasDeNegocio.get(nmClasse);

		if (regrasOperacao != null) {
			List<IStrategy> regras = regrasOperacao.get(operacao);

			if (regras != null) {
				for (IStrategy strategies : regras) {
					String mensagemValidacao = strategies.processar(entidade);

					if (mensagemValidacao != null) {
						msg.append(mensagemValidacao);
						msg.append("\n");
					}
				}
			}

		}

		if (msg.length() > 0)
			return msg.toString();
		else
			return null;
	}


}
