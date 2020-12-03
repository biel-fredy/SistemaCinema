package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.aplicacao.EntidadeAplicacao;
import core.aplicacao.Resultado;
import core.util.db.DB;
import dominio.EntidadeDominio;
import web.command.ICommand;
import web.command.impl.AlterarCommand;
import web.command.impl.ConsultarCommand;
import web.command.impl.ExcluirCommand;
import web.command.impl.SalvarCommand;
import web.vh.IViewHelper;
import web.vh.impl.ConhecimentoViewHelper;
import web.vh.impl.FilmeViewHelper;
import web.vh.impl.FuncionarioViewHelper;
import web.vh.impl.LoginViewHelper;

/**
 * Servlet implementation class ServletCinema
 */
@WebServlet(description = "Servlet do Sistema Cinema", urlPatterns = { "/ServletCinema" })
public class ServletCinema extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Map<String, ICommand> commands;
	private static Map<String, IViewHelper> vhs;
	private static Connection conn = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCinema() {
        super();
        commands = new HashMap<String, ICommand>(); 	
    	commands.put("SALVAR", new SalvarCommand());
    	commands.put("EXCLUIR", new ExcluirCommand());
    	commands.put("CONSULTAR", new ConsultarCommand());
    	//commands.put("VISUALIZAR", new VisualizarCommand());
    	commands.put("ALTERAR", new AlterarCommand());
    	
    	//HashMap de Views(MapeamentoServlet, ViewHelper)
    	vhs = new HashMap<String, IViewHelper>();
    	vhs.put("/SistemaCinema/Login", new LoginViewHelper());
    	vhs.put("/SistemaCinema/SalvarFilme", new FilmeViewHelper());
    	vhs.put("/SistemaCinema/SalvarSessao", new ConhecimentoViewHelper());
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcessRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcessRequest(request, response);
	}
	
	protected void doProcessRequest(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("eitalele");

		String contextPath = request.getContextPath();
		System.out.println(contextPath);
		EntidadeAplicacao.setContextPath(contextPath);
	
		//Obt�m a uri que invocou esta servlet (O que foi definido no methdo do form html)
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		//Obt�m a opera��o executada
		String operacao = request.getParameter("autenticar");
		System.out.println(operacao);
		
		//Obt�m um viewhelper indexado pela uri que invocou esta servlet
		IViewHelper vh = vhs.get(uri);
		System.out.println("Eita");
		
		//O viewhelper retorna a entidade especifica para a tela que chamou esta servlet
		EntidadeDominio entidade =  vh.getEntidade(request);
		System.out.println(entidade);

		//Obt�m o command para executar a respectiva opera��o
		ICommand command = commands.get(operacao);
		System.out.println("eita2");
		
		/*Executa o command que chamar� a fachada para executar a opera��o requisitada
		 * o retorno � uma inst�ncia da classe resultado que pode conter mensagens derro 
		 * ou entidades de retorno
		 */
		Resultado resultado = command.execute(entidade);
		
		/*
		 * Executa o m�todo setView do view helper espec�fico para definir como dever� ser apresentado 
		 * o resultado para o usu�rio
		 */
		vh.setView(resultado, request, response);

	}

	public void destroy(){
		DB.closeConnection();
	}
}
