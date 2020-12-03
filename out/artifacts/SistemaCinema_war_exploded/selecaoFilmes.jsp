<%@page import="core.aplicacao.EntidadeAplicacao"%>
<%@page import="dominio.entidades.Filme"%>
<%@page import="dominio.EntidadeDominio"%>
<%@page import="java.util.List"%>
<%@page import="web.vh.impl.SelecaoFilmesViewHelper"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>SistemaCinema</title>
		<link rel="stylesheet" type="text/css" href="lib/css/form.css"/>
	</head>
	<body>
		<table border=1>
			<tr>
				<%
					SelecaoFilmesViewHelper vhFilmes = new SelecaoFilmesViewHelper();
		        	EntidadeAplicacao.montarCache();
					List<EntidadeDominio> listaFilmes =  vhFilmes.getFilmesCartaz();
					for(EntidadeDominio filme : listaFilmes){
						Filme f = (Filme) filme;
						out.print("<td>");
						out.print("<img src=" + f.getUri() + "alt=" + f.getNome() + "width=300 height=450");
						out.print("</td>");
					}
				%>
			</tr>
			<tr>
				<%
					for(EntidadeDominio filme : listaFilmes){
						Filme f = (Filme) filme;
						out.print("<td>");
						out.print("<a href=\"formFilme.jsp\">" + f.getNome());
						out.print("</td>");
					}
				%>
			</tr>
			<tr>
				<%
					for(EntidadeDominio filme : listaFilmes){
						Filme f = (Filme) filme;
						out.print("<td>");
						out.print(f.getDiretor());
						out.print("</td>");
					}
				%>
			</tr>
			<tr>
				<%
					for(EntidadeDominio filme : listaFilmes){
						Filme f = (Filme) filme;
						out.print("<td>");
						out.print(f.getClassificacaoIndicativa());
						out.print("</td>");
					}
				%>
			</tr>
			<tr>
				<%
					for(EntidadeDominio filme : listaFilmes){
						Filme f = (Filme) filme;
						out.print("<td>");
						out.print(f.getTempoDuracao());
						out.print("</td>");
					}
				%>
			</tr>
		</table>
	</body>
</html>