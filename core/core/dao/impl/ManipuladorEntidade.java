package core.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import core.dao.anotacoes.Coluna;
import core.dao.anotacoes.MuitosPraUm;
import core.dao.anotacoes.NaoGravar;
import core.dao.anotacoes.Tabela;
import core.dao.anotacoes.UmPraMuitos;
import dominio.EntidadeDominio;

public class ManipuladorEntidade {

	private EntidadeDominio entidade = null;
	private String nomeTabela = null;
	private String trigama = null;
	private Map<String, String> mapColunas = new HashMap<String, String>();

	public ManipuladorEntidade(EntidadeDominio entidade) {
		setEntidade(entidade);
		setDadosTabela();
		setMapColunas();
	}

	public EntidadeDominio getEntidade() {
		return entidade;
	}

	public void setEntidade(EntidadeDominio entidade) {
		this.entidade = entidade;
	}

	public String getNomeTabela() {
		return nomeTabela;
	}

	public void setDadosTabela() {
		Class<? extends EntidadeDominio> classeDoObjeto = this.entidade.getClass();
		String nomeTabela;
		Tabela tabela = (Tabela) classeDoObjeto.getDeclaredAnnotation(Tabela.class);
		if (tabela != null) {
			nomeTabela = tabela.nomeTabela();
			if (tabela.trigama() != null) {
				this.trigama = tabela.trigama();
			}
		} else {
			nomeTabela = classeDoObjeto.getSimpleName().toLowerCase();
		}
		this.nomeTabela = nomeTabela;
	}

	public Map<String, String> getMapColunas() {
		return mapColunas;
	}

	public void setMapColunas() {
		this.mapColunas = geraMapColunas(this.entidade, this.nomeTabela, this.trigama);
	}

	public String getTrigama() {
		return this.trigama;
	}

	public Map<String, String> geraMapColunas(EntidadeDominio entidade, String nomeTabela, String trigama) {
		Class<? extends EntidadeDominio> classeDoObjeto = entidade.getClass();
		Class<?> sprClasseDoObjeto = classeDoObjeto.getSuperclass();
		Map<String, String> listaColunas = new LinkedHashMap<String, String>();
		String nomeColuna;
		String valorAtributo;

		for (Field atributoED : sprClasseDoObjeto.getDeclaredFields()) {
			NaoGravar naoGravar = (NaoGravar) atributoED.getDeclaredAnnotation(NaoGravar.class);
			if (null == naoGravar) {
				if ("id".equals(atributoED.getName())) {
					if (null != trigama) {
						nomeColuna = trigama + "_" + atributoED.getName();
					} else {
						nomeColuna = atributoED.getName() + nomeTabela;
					}
				} else {
					if (null != trigama) {
						nomeColuna = trigama + "_" + atributoED.getName();
					} else {
						nomeColuna = atributoED.getName();
					}
				}
				valorAtributo = geraValorAtributo(atributoED, entidade);
				listaColunas.put(nomeColuna, valorAtributo);
			}
		}

		for (Field atributoE : classeDoObjeto.getDeclaredFields()) {
			NaoGravar naoGravar = (NaoGravar) atributoE.getDeclaredAnnotation(NaoGravar.class);
			UmPraMuitos umPraMuitos = (UmPraMuitos) atributoE.getDeclaredAnnotation(UmPraMuitos.class);
			// ChaveEstrangeira chaveEstrangeira = (ChaveEstrangeira)
			// atributoE.getDeclaredAnnotation(ChaveEstrangeira.class);
			if (naoGravar == null && umPraMuitos == null) {
				nomeColuna = geraNomeColuna(atributoE);
				valorAtributo = geraValorAtributo(atributoE, entidade);
				listaColunas.put(nomeColuna, valorAtributo);
			}
		}
		return listaColunas;
	}

	public String geraNomeColuna(Field atributoE) {
		Coluna coluna = (Coluna) atributoE.getDeclaredAnnotation(Coluna.class);
		MuitosPraUm muitosPraUm = (MuitosPraUm) atributoE.getDeclaredAnnotation(MuitosPraUm.class);
		String nomeColuna = null;

		if (coluna != null) {
			nomeColuna = coluna.value();
		} else if (muitosPraUm != null) {
			nomeColuna = muitosPraUm.mappedBy();
		} else {
			if (trigama != null) {
				nomeColuna = trigama + "_" + atributoE.getName();
			} else {
				nomeColuna = atributoE.getName();
			}
		}
		return nomeColuna;
	}

	public String geraValorAtributo(Field atributo, EntidadeDominio entidade) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		// Annotations
		MuitosPraUm muitosPraUm = (MuitosPraUm) atributo.getDeclaredAnnotation(MuitosPraUm.class);

		String valorAtributo = null;
		atributo.setAccessible(true);
		try {
			if (atributo.get(entidade) != null) {

				valorAtributo = atributo.get(entidade).toString();
				if (muitosPraUm != null) {
					Object campoEntidade = atributo.getType().getConstructor().newInstance();
					campoEntidade = atributo.get(entidade);
					int id = 0;
					Field campoId = campoEntidade.getClass().getSuperclass().getDeclaredField("id");
					if (campoId.trySetAccessible()) {
						if (null != campoId.get(campoEntidade)) {
							id = (int) campoId.get(campoEntidade);
						}
					}
					campoId.setAccessible(false);
					valorAtributo = Integer.toString(id);
				} else if (isValidDate(valorAtributo)) {
					valorAtributo = "'" + sdf.format(atributo.get(entidade)) + "'";
				} else if (atributo.getType().isEnum()) {
					String nomeMetodo = "getCod";
					Method getCod = atributo.getType().getDeclaredMethod(nomeMetodo);
					Integer cod = (Integer) getCod.invoke(atributo.get(entidade));
					valorAtributo = cod.toString();
				} else if (!atributo.getType().equals(Boolean.class)) {
					valorAtributo = "'" + valorAtributo + "'";
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} finally {
			atributo.setAccessible(false);
		}
		return valorAtributo;
	}

	public LinkedHashMap<String, List<EntidadeDominio>> criarMapUmPraMuitos(EntidadeDominio entidade) {
		LinkedHashMap<String, List<EntidadeDominio>> mapUmPraMuitos = new LinkedHashMap<String, List<EntidadeDominio>>();
		// List<List<EntidadeDominio>> listaUmPraMuitos = new
		// ArrayList<List<EntidadeDominio>>();
		List<EntidadeDominio> listaEntidades = new ArrayList<EntidadeDominio>();
		Class<? extends EntidadeDominio> classeEntidade = entidade.getClass();
		for (Field atributo : classeEntidade.getDeclaredFields()) {
			UmPraMuitos umPraMuitos = (UmPraMuitos) atributo.getDeclaredAnnotation(UmPraMuitos.class);
			if (umPraMuitos != null) {
				String nomeAtributo = atributo.getName();
				nomeAtributo = nomeAtributo.substring(0, 1).toUpperCase().concat(nomeAtributo.substring(1));
				String nomeMetodo = "get" + nomeAtributo;
				try {
					Method getLista = classeEntidade.getDeclaredMethod(nomeMetodo);
					listaEntidades = (List<EntidadeDominio>) getLista.invoke(entidade);
					mapUmPraMuitos.put(umPraMuitos.mappedBy(), listaEntidades);
				} catch (NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}

		}
		return mapUmPraMuitos;
	}

	public Boolean isValidDate(String inDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(inDate.trim());
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}

	public void addMap(String nomeColuna, String valor) {
		this.mapColunas.put(nomeColuna, valor);
	}

}
