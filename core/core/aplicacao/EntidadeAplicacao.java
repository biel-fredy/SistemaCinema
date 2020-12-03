package core.aplicacao;

import java.util.ArrayList;
import java.util.List;

import core.dao.impl.GerenciadorDAOGenerico;
import dominio.EntidadeDominio;
import dominio.entidades.Filme;

public class EntidadeAplicacao {
	
	//private String user;
	//private String senha;
	private static List<EntidadeDominio> listaFilmesEmCartaz = new ArrayList<EntidadeDominio>();
	private static String contextPath;
	
	public static boolean verificarAutenticacao() {
		return true;
	}
	
	public static void montarCache() {
		GerenciadorDAOGenerico dao = new GerenciadorDAOGenerico();
		
		//Filmes Em cartaz
		boolean emCartaz = true;
		Filme filmeEmCartaz = new Filme(emCartaz);
		Resultado resultado = new Resultado();
		resultado = dao.consultar(filmeEmCartaz);
		setListaFilmesEmCartaz(resultado.getResultadoLista());
	}

	public static List<EntidadeDominio> getListaFilmesEmCartaz() {
		return listaFilmesEmCartaz;
	}

	public static void setListaFilmesEmCartaz(List<EntidadeDominio> listaFilmes) {
		listaFilmesEmCartaz = listaFilmes;
	}

	public static String getContextPath() {
		return contextPath;
	}

	public static void setContextPath(String contextPath) {
		EntidadeAplicacao.contextPath = contextPath;
	}
}
