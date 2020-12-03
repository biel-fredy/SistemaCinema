package core.dao.impl;

import core.aplicacao.Resultado;
import dominio.EntidadeDominio;

public class GerenciadorDAOGenerico{
	
	public Resultado salvar(EntidadeDominio entidade) {
		GeradorSQL geradorSQL = new GeradorSQL();
		
		return geradorSQL.salvar(entidade, null, null);

	}
	
	public Resultado alterar(EntidadeDominio entidade) {
		GeradorSQL geradorSQL = new GeradorSQL();
		
		return geradorSQL.alterar(entidade, null, null);
	}

	public Resultado excluir(EntidadeDominio entidade) {
		GeradorSQL geradorSQL = new GeradorSQL();
		
		return geradorSQL.excluir(entidade, null, null);
	}

	public Resultado consultar(EntidadeDominio entidade) {
		GeradorSQL geradorSQL = new GeradorSQL();
		
		return geradorSQL.consultar(entidade, null, null);
	}
}
