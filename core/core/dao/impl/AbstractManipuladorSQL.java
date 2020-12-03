package core.dao.impl;

import java.util.Map;

import core.dao.IManipuladorSQL;

public abstract class AbstractManipuladorSQL implements IManipuladorSQL{
	
	protected String sqlColunas = "";
	protected String sqlValues = "";
	protected String sqlWhere = "";
		
	public AbstractManipuladorSQL(Map<String, String> mapColunas) {
		super();
		montaSqlColunas(mapColunas);
		montaSqlValues(mapColunas);
		montaSqlWhere(mapColunas);
	}
	
	public String getSqlColunas() {
		return sqlColunas;
	}
	public void setSqlColunas(String sqlColunas) {
		this.sqlColunas = sqlColunas;
	}
	public String getSqlValues() {
		return sqlValues;
	}
	public void setSqlValues(String sqlValues) {
		this.sqlValues = sqlValues;
	}
	public String getSqlWhere() {
		return sqlWhere;
	}
	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}
	
	public void montaSqlColunas(Map<String, String> mapColunasCampos) {
		for (String key : mapColunasCampos.keySet()) {
			this.sqlColunas += key + ", ";
		}
	}

	public void montaSqlValues(Map<String, String> mapColunasCampos) {
		for (String key : mapColunasCampos.keySet()) {
			this.sqlValues += mapColunasCampos.get(key) + ", ";
		}
	}
	
	public void montaSqlWhere(Map<String, String> mapColunasCampos) {
		for (String key : mapColunasCampos.keySet()) {
			if (mapColunasCampos.get(key) != null) {
				this.sqlWhere += key + " = " + mapColunasCampos.get(key) + ", ";
			}
		}
	}
}
