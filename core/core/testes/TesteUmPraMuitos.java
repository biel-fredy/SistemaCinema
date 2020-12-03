package core.testes;

import java.util.Date;

import core.aplicacao.Resultado;
import core.dao.impl.GerenciadorDAOGenerico;
import core.util.db.DB;
import dominio.entidades.Filme;
import dominio.entidades.SalaExibicao;
import dominio.entidades.Sessao;
import dominio.entidadesenum.ClassificacaoIndicativa;
import dominio.entidadesenum.DiaDaSemana;
import dominio.entidadesenum.Periodo;
import dominio.entidadesenum.TipoSala;

public class TesteUmPraMuitos {

	public static void main(String[] args) {
	/*	
		Chamado chamado = new Chamado();
		
		//Preenche Chamado
		chamado.setId(2);
		
		Anexo anexo = new Anexo();
		
		chamado.addAnexo(anexo);
		
		DAOGenerico dao = new DAOGenerico();
		
		dao.consultar(chamado);
		
		DB.closeConnection();
*/
		System.out.println("teste1");
		Filme rocky = new Filme("Rocky: Um Lutador", "John G. Avildsen", ClassificacaoIndicativa.DEZ_ANOS, 122, true);
		SalaExibicao sala = new SalaExibicao(2, TipoSala.NORMAL, "Touro");
		Sessao sessao1 = new Sessao(rocky, new Date(), DiaDaSemana.SEGUNDA_FEIRA, Periodo.NOITE, 14.50d);
		Sessao sessao2 = new Sessao(rocky, new Date(), DiaDaSemana.TERCA_FEIRA, Periodo.NOITE, 11.50d);

		sala.addSessao(sessao1);
		sala.addSessao(sessao2);
		
		GerenciadorDAOGenerico dao = new GerenciadorDAOGenerico();
		
		Resultado teste1 = dao.salvar(rocky);
		Resultado testeResultado = dao.salvar(sala);
		
		System.out.println(testeResultado.getMensagem());
		System.out.println(teste1.getMensagem());
		DB.closeConnection();
	}

}
