package core.impl.negocio;

import core.IStrategy;
import dominio.EntidadeDominio;
import dominio.entidades.Filme;
import dominio.entidadesenum.ClassificacaoIndicativa;

public class ValidadorDadosObrigatoriosFilme implements IStrategy {
    @Override
    public String processar(EntidadeDominio entidade) {
        if (entidade instanceof Filme) {
            Filme filme = (Filme) entidade;

            String nome = filme.getNome();
            String diretor = filme.getDiretor();
            ClassificacaoIndicativa classificacaoInd = filme.getClassificacaoIndicativa();
            int tempoDuracao = filme.getTempoDuracao();

            if (nome == null) {
                return "Nome � de preenchimento obrigat�rio!";
            }

            if (nome.trim().equals("")) {
                return "Nome � de preenchimento obrigat�rio!";
            }

            if (diretor == null) {
                return "Diretor � de preenchimento obrigat�rio!";
            }

            if (diretor.trim().equals("")) {
                return "Diretor � de preenchimento obrigat�rio!";
            }

            if (classificacaoInd == null) {
                return "Classifica��o Indicativa � de preenchimento obrigat�rio!";
            }

            if (tempoDuracao == 0) {
                return "Tempo Dura��o � de preenchimento obrigat�rio!";
            }

        } else {
            return "Deve ser um filme!";
        }

        return null;
    }
}
