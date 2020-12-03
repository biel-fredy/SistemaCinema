package web.vh.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.aplicacao.Resultado;
import dominio.EntidadeDominio;
import dominio.entidades.Usuario;
import web.vh.IViewHelper;

public class LoginViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String login = request.getParameter("txtLogin");
		String senha = request.getParameter("txtSenha");
		
		Usuario usu = new Usuario();
		usu.setLogin(login);
		usu.setSenha(senha);

		return usu;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

}
