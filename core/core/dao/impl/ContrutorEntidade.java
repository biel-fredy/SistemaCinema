package core.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.dao.anotacoes.MuitosPraUm;
import core.dao.anotacoes.UmPraMuitos;
import dominio.EntidadeDominio;

public class ContrutorEntidade {
	
	private EntidadeDominio entidade;
	private Map<String, String> mapColunas = new HashMap<String, String>();
	
	public ContrutorEntidade(Map<String, String> mapColunas, EntidadeDominio entidade) {
		this.mapColunas = mapColunas;
		this.entidade = entidade;
	}
	
	public List<EntidadeDominio> construirEntidades(ResultSet resultadosQuery) {
		Class<? extends EntidadeDominio> classeDoObjeto = this.entidade.getClass();
		Class<?> sprClasseDoObjeto = classeDoObjeto.getSuperclass();
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		
		try {
			if (resultadosQuery.isClosed()) {
				System.out.println("Uai");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			int numeroColunas = resultadosQuery.getMetaData().getColumnCount();
			while (resultadosQuery.next()) {
				Object obj = classeDoObjeto.getConstructor().newInstance();
				int coluna = 1;
				for (int i = 1; i <= numeroColunas; i++) {
					if (coluna <= sprClasseDoObjeto.getDeclaredFields().length) {
						for (Field atributoEntidadeDominio : sprClasseDoObjeto.getDeclaredFields()) {
							atributoEntidadeDominio.setAccessible(true);
							atributoEntidadeDominio.set(obj, resultadosQuery.getObject(coluna++));
							atributoEntidadeDominio.setAccessible(false);
						}
					} else {
						for (Field atributo : classeDoObjeto.getDeclaredFields()) {
							if (coluna <= this.mapColunas.size()) {
								MuitosPraUm muitosPraUm = atributo.getDeclaredAnnotation(MuitosPraUm.class);
								UmPraMuitos umPraMuitos = atributo.getDeclaredAnnotation(UmPraMuitos.class);
								atributo.setAccessible(true);
								if (muitosPraUm != null) {
									Object campoEntidade = atributo.getType().getConstructor().newInstance();
									int id = (int) resultadosQuery.getObject(coluna++);
									Method setId = campoEntidade.getClass().getSuperclass().getDeclaredMethod("setId",
											Integer.class);
									setId.invoke(campoEntidade, id);
									GeradorSQL geraSQL = new GeradorSQL();
									List<EntidadeDominio> listaCampo = geraSQL
											.consultar((EntidadeDominio) campoEntidade, muitosPraUm.mappedBy(), id)
											.getResultadoLista();
									if (!listaCampo.isEmpty()) {
										campoEntidade = listaCampo.get(0);
									}
									atributo.set(obj, campoEntidade);
								} else if (umPraMuitos != null) {
									coluna++;
								} else if (atributo.getType().isEnum()) {
									Method toEnum = atributo.getType().getMethod("toEnum", Integer.class);
									Object value = toEnum.invoke(null, resultadosQuery.getObject(coluna++));
									atributo.set(obj, value);
								} else {
									atributo.set(obj, resultadosQuery.getObject(coluna++));
								}
								atributo.setAccessible(false);
							}
						}
					}
				}
				lista.add((EntidadeDominio) obj);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return lista;

	}

}
