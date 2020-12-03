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
	<form action="Login" method="post">
		<fieldset class="grupo">
			<div class="campo">
				<label for="txtLogin">Login:</label>
				<input type="text" id="txtLogin" name="txtLogin"/>
			</div>
			<div class="campo">
				<label for="txtSenha">Senha:</label>
				<input type="text" id="txtSenha" name="txtSenha"/>
			</div>
		</fieldset>
		<input type="submit" id="operacao" name="operacao" value="Autenticar Usuario"/>
		<button name="autenticar" value="CONSULTAR" type="submit" id="teste">Teste</button>
	</form> 
</body>
</html>