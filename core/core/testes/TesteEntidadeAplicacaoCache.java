package core.testes;

import java.util.Calendar;
import java.util.List;

import core.aplicacao.EntidadeAplicacao;
//import core.dao.impl.GerenciadorDAOGenerico;
import core.util.db.DB;
import dominio.EntidadeDominio;
import dominio.entidades.Filme;
import dominio.entidadesenum.ClassificacaoIndicativa;

public class TesteEntidadeAplicacaoCache {

	public static void main(String[] args) {
		
		System.out.println("Teste");
		
		//GerenciadorDAOGenerico dao = new GerenciadorDAOGenerico();
		Calendar c = Calendar.getInstance();
		
		//Blade Runner
		Filme bladeRunner = new Filme(true);
		bladeRunner.setClassificacaoIndicativa(ClassificacaoIndicativa.DEZ_ANOS);
		bladeRunner.setDataCriacao(c.getTime());
		bladeRunner.setDiretor("Harrison Ford");
		bladeRunner.setNome("Blade Runner");
		bladeRunner.setTempoDuracao(222);
		bladeRunner.setUri("lib/img/cartazes/bladeRunner.jpg");
		
		//Nascido para Matar
		Filme nascidoMatar = new Filme(true);
		nascidoMatar.setClassificacaoIndicativa(ClassificacaoIndicativa.DEZOITO_ANOS);
		nascidoMatar.setDataCriacao(c.getTime());
		nascidoMatar.setDiretor("Stanley Kubrick");
		nascidoMatar.setNome("Nascido Para Matar");
		nascidoMatar.setTempoDuracao(240);
		nascidoMatar.setUri("lib/img/cartazes/nascidoParaMatar.jpg");
		
		//O Exorcista
		Filme oExorcista = new Filme(true);
		oExorcista.setClassificacaoIndicativa(ClassificacaoIndicativa.DEZOITO_ANOS);
		oExorcista.setDataCriacao(c.getTime());
		oExorcista.setDiretor("William Friedkin");
		oExorcista.setNome("O Exorcista");
		oExorcista.setTempoDuracao(133);
		oExorcista.setUri("lib/img/cartazes/oExorcista.jpg");
		
		//dao.salvar(bladeRunner);
		//dao.salvar(nascidoMatar);
		//dao.salvar(oExorcista);
		
		EntidadeAplicacao.montarCache();
		
		List<EntidadeDominio> filmes = EntidadeAplicacao.getListaFilmesEmCartaz();
		
		for (EntidadeDominio f : filmes) {
			System.out.println(f);
		}
		
		DB.closeConnection();
	}

}
