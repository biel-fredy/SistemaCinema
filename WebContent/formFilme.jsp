<%@ page import="dominio.entidadesenum.ClassificacaoIndicativa" %>
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
<form action="SalvarFilme" method="post">
    <fieldset class="grupo">
        <div class="campo">
            <label for="txtNomeFilme">Nome do Filme:</label>
            <input type="text" id="txtNomeFilme" name="txtNomeFilme"/>
        </div>
        <div class="campo">
            <label for="txtNomeDiretor">Nome do Diretor:</label>
            <input type="text" id="txtNomeDiretor" name="txtNomeDiretor"/>
        </div>
    </fieldset>
    <fieldset>
        <div class="select">
            <label for="comboBoxClassInd">Classificação Indicativa:</label>
            <select id="comboBoxClassInd">
                <%
                    for(ClassificacaoIndicativa ci : ClassificacaoIndicativa.values()){
                        out.print("<option value=\"" + ci.getCod() + "\">" + ci.getDescricao() + "</option>");
                    }
                %>
            </select>
        </div>
    </fieldset>
    <fieldset>
        <div class="campo">
            <label for="txtDuracao">Tempo de duração(em minutos):</label>
            <input type="text" id="txtDuracao" name="txtDuracao"/>
        </div>
    </fieldset>
    <fieldset>
        <div class="campo">
            <label for="radioEmCartaz">Em cartaz?</label>
            <input type="radio" id="radioEmCartazSim" name="radioEmCartaz" value="1"/> Sim
            <br>
            <input type="radio" id="radioEmCartazNao" name="radioEmCartaz" value="0"/> Não
        </div>
    </fieldset>
    <button name="salvarFilme" value="SALVAR" type="submit" id="salvarFilme">Salvar</button>
</form>
</body>
</html>