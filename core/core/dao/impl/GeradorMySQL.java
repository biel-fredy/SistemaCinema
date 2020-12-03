package core.dao.impl;

import java.util.Map;

import core.util.db.DbException;

public class GeradorMySQL extends AbstractManipuladorSQL {
	
	public GeradorMySQL(Map<String, String> mapColunas) {
		super(mapColunas);
	}

	@Override
	public String montaSQLInsert(String nomeTabela) {
		String sqlInsert = "insert into " + nomeTabela + " (";
		sqlColunas = sqlColunas.substring(0, sqlColunas.length() - 2);
		sqlValues = sqlValues.substring(0, sqlValues.length() - 2);
		sqlInsert += sqlColunas + ") values (" + sqlValues + ")"; 
		return sqlInsert;
	}

	@Override
	public String montaSQLUpdate(String nomeTabela, Map<String, String> mapColunasCampos) {
		String keyId = "id" + nomeTabela;
		String sqlUpdate = "update " 
							+ nomeTabela 
							+ " set ";
		for (String key : mapColunasCampos.keySet()) {
			if (!key.equals(keyId) ) {
				sqlUpdate += key + " = " + mapColunasCampos.get(key) + ", ";
			}
		}
		sqlUpdate = sqlUpdate.substring(0, sqlUpdate.length() - 2);
		if (mapColunasCampos.containsKey(keyId)) {
			sqlUpdate += " where " + keyId + " = " + mapColunasCampos.get(keyId);
		}
		else {
			throw new DbException("Não é possível realizar um update sem WHERE!");
		}
		return sqlUpdate;
	}

	@Override
	public String montaSQLDelete(String nomeTabela) {
		String sqlDelete = "delete from " + nomeTabela;
		if (!sqlWhere.isBlank()) {
			sqlWhere = sqlWhere.substring(0, sqlWhere.length() - 2);
			sqlDelete += " where " + sqlWhere;
		}
		else {
			throw new DbException("Não é possível realizar um delete sem WHERE!");
		}
		return sqlDelete;
	}
	
	@Override
	public String montaSQLSelect(String nomeTabela) {
		String sqlSelect = "select ";
		sqlColunas = sqlColunas.substring(0, sqlColunas.length() - 2);
		sqlSelect += sqlColunas + " from " + nomeTabela;
		if (!sqlWhere.isBlank()) {
			sqlWhere = sqlWhere.substring(0, sqlWhere.length() - 2);
			sqlSelect += " where " + sqlWhere;
		}
		return sqlSelect;
	}
}
