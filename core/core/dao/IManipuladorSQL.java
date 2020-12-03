package core.dao;

import java.util.Map;

public interface IManipuladorSQL {
	
	String montaSQLInsert(String nomeTabela);
	String montaSQLUpdate(String nomeTabela, Map<String, String> mapColunasCampos);
	String montaSQLDelete(String nomeTabela);
	String montaSQLSelect(String nomeTabela);
}
