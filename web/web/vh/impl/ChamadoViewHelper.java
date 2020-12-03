package web.vh.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.aplicacao.Resultado;
import dominio.EntidadeDominio;
import web.vh.IViewHelper;

public class ChamadoViewHelper implements IViewHelper {
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

}
