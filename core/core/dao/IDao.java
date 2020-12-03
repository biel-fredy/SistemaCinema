package core.dao;

import core.aplicacao.Resultado;
import dominio.EntidadeDominio;

public interface IDao {
	public Resultado salvar(EntidadeDominio entidade);
	public Resultado alterar(EntidadeDominio entidade);
	public Resultado excluir(EntidadeDominio entidade);
	public Resultado consultar(EntidadeDominio entidade);
	public Resultado consultarPorID(Integer id, EntidadeDominio entidade);
}
