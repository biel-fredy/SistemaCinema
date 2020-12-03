package web.command.impl;

import core.aplicacao.Resultado;
import dominio.EntidadeDominio;

public class AlterarCommand extends AbstractCommand {

	@Override
	public Resultado execute(EntidadeDominio entidade) {
		return fachada.alterar(entidade);
	}

}
