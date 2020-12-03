package core.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import core.aplicacao.Resultado;
import core.dao.IManipuladorSQL;
import core.dao.impl.enums.BancoDados;
import dominio.EntidadeDominio;

public class GeradorSQL {

	private BancoDados bancoDados = BancoDados.MYSQL;

	public IManipuladorSQL getManipuladorSQL(Map<String, String> mapColunas) {
		if (this.bancoDados == BancoDados.ORACLE) {
			return new GeradorOracleSQL(mapColunas);
		} else if (this.bancoDados == BancoDados.MYSQL) {
			return new GeradorMySQL(mapColunas);
		} else if (this.bancoDados == BancoDados.SQL_SERVER) {
			return new GeradorSQLServer(mapColunas);
		}
		return null;
	}

	public Resultado salvar(EntidadeDominio entidade, String colunaEstrangeira, Integer id) {
		Resultado resultado = new Resultado();
		StringBuilder sb = new StringBuilder();
		ResultadoDAO resultadoDAO = null;
		ManipuladorEntidade manipuladorEntidade = new ManipuladorEntidade(entidade);

		if (colunaEstrangeira != null) {
			if (id != null) {
				String idString = "'" + id.toString() + "'";
				manipuladorEntidade.addMap(colunaEstrangeira, idString);
			}
		}

		IManipuladorSQL manipuladorSQL = getManipuladorSQL(manipuladorEntidade.getMapColunas());

		String sqlInsert = manipuladorSQL.montaSQLInsert(manipuladorEntidade.getNomeTabela());

		DAOGenerico dao = new DAOGenerico();

		resultadoDAO = dao.salvar(sqlInsert);

		entidade.setId(resultadoDAO.getIdGerado());

		if (entidade != null) {
			resultado.addEntidade(entidade);
		}
		sb.append(resultadoDAO.getMsg());

		// System.out.println(resultadoDAO.getMsg());

		LinkedHashMap<String, List<EntidadeDominio>> mapUmPraMuitos = manipuladorEntidade.criarMapUmPraMuitos(entidade);

		if (mapUmPraMuitos.size() != 0) {
			GeradorSQL geradorSQL = new GeradorSQL();
			Resultado resultadoUmPraMtos = new Resultado();

			for (String nomeColuna : mapUmPraMuitos.keySet()) {
				for (EntidadeDominio entidadeD : mapUmPraMuitos.get(nomeColuna)) {
					resultadoUmPraMtos = geradorSQL.salvar(entidadeD, nomeColuna, entidade.getId());
					resultado.addEntidade(resultadoUmPraMtos.getResultadoLista().get(0));
					sb.append(resultadoUmPraMtos.getMensagem());
				}
			}
		}
		resultado.setMensagem(sb.toString());
		return resultado;
	}

	public Resultado alterar(EntidadeDominio entidade, String colunaEstrangeira, Integer id) {
		Resultado resultado = new Resultado();
		StringBuilder sb = new StringBuilder();
		ResultadoDAO resultadoDAO = null;
		ManipuladorEntidade manipuladorEntidade = new ManipuladorEntidade(entidade);

		if (colunaEstrangeira != null) {
			if (id != null) {
				String idString = "'" + id.toString() + "'";
				manipuladorEntidade.addMap(colunaEstrangeira, idString);
			}
		}

		IManipuladorSQL manipuladorSQL = getManipuladorSQL(manipuladorEntidade.getMapColunas());

		String sqlUpdate = manipuladorSQL.montaSQLUpdate(manipuladorEntidade.getNomeTabela(),
				manipuladorEntidade.getMapColunas());

		DAOGenerico dao = new DAOGenerico();

		resultadoDAO = dao.alterar(sqlUpdate);

		entidade.setId(resultadoDAO.getIdGerado());

		resultado.addEntidade(entidade);
		sb.append(resultadoDAO.getMsg());

		LinkedHashMap<String, List<EntidadeDominio>> mapUmPraMuitos = manipuladorEntidade.criarMapUmPraMuitos(entidade);

		if (mapUmPraMuitos.size() != 0) {
			GeradorSQL geradorSQL = new GeradorSQL();
			Resultado resultadoUmPraMtos = new Resultado();

			for (String nomeColuna : mapUmPraMuitos.keySet()) {
				for (EntidadeDominio entidadeD : mapUmPraMuitos.get(nomeColuna)) {
					resultadoUmPraMtos = geradorSQL.alterar(entidadeD, nomeColuna, entidade.getId());
					resultado.addEntidade(resultadoUmPraMtos.getResultadoLista().get(0));
					sb.append(resultadoUmPraMtos.getMensagem());
				}
			}
		}
		return resultado;
	}

	public Resultado excluir(EntidadeDominio entidade, String colunaEstrangeira, Integer id) {
		Resultado resultado = new Resultado();
		StringBuilder sb = new StringBuilder();
		ResultadoDAO resultadoDAO = null;
		ManipuladorEntidade manipuladorEntidade = new ManipuladorEntidade(entidade);

		if (colunaEstrangeira != null) {
			if (id != null) {
				String idString = "'" + id.toString() + "'";
				manipuladorEntidade.addMap(colunaEstrangeira, idString);
			}
		}

		IManipuladorSQL manipuladorSQL = getManipuladorSQL(manipuladorEntidade.getMapColunas());

		String sqlInsert = manipuladorSQL.montaSQLInsert(manipuladorEntidade.getNomeTabela());

		DAOGenerico dao = new DAOGenerico();

		resultadoDAO = dao.salvar(sqlInsert);

		entidade.setId(resultadoDAO.getIdGerado());

		resultado.addEntidade(entidade);
		sb.append(resultadoDAO.getMsg());

		LinkedHashMap<String, List<EntidadeDominio>> mapUmPraMuitos = manipuladorEntidade.criarMapUmPraMuitos(entidade);

		if (mapUmPraMuitos.size() != 0) {
			GeradorSQL geradorSQL = new GeradorSQL();
			Resultado resultadoUmPraMtos = new Resultado();

			for (String nomeColuna : mapUmPraMuitos.keySet()) {
				for (EntidadeDominio entidadeD : mapUmPraMuitos.get(nomeColuna)) {
					resultadoUmPraMtos = geradorSQL.excluir(entidadeD, nomeColuna, entidade.getId());
					resultado.addEntidade(resultadoUmPraMtos.getResultadoLista().get(0));
					sb.append(resultadoUmPraMtos.getMensagem());
				}
			}
		}
		return resultado;
	}

	public Resultado consultar(EntidadeDominio entidade, String colunaEstrangeira, Integer id) {
		Resultado resultado = new Resultado();
		ManipuladorEntidade manipuladorEntidade = new ManipuladorEntidade(entidade);

		if (colunaEstrangeira != null) {
			if (id != null) {
				String idString = "'" + id.toString() + "'";
				manipuladorEntidade.addMap(colunaEstrangeira, idString);
			}
		}

		ContrutorEntidade construtorEntidade = new ContrutorEntidade(manipuladorEntidade.getMapColunas(), entidade);
		
		IManipuladorSQL manipuladorSQL = getManipuladorSQL(manipuladorEntidade.getMapColunas());

		String sqlSelect = manipuladorSQL.montaSQLSelect(manipuladorEntidade.getNomeTabela());

		DAOGenerico dao = new DAOGenerico();
		
		resultado = dao.consultar(sqlSelect, construtorEntidade);

		return resultado;
	}

}
