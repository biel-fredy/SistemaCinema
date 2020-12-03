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
                return "Nome é de preenchimento obrigatório!";
            }

            if (nome.trim().equals("")) {
                return "Nome é de preenchimento obrigatório!";
            }

            if (diretor == null) {
                return "Diretor é de preenchimento obrigatório!";
            }

            if (diretor.trim().equals("")) {
                return "Diretor é de preenchimento obrigatório!";
            }

            if (classificacaoInd == null) {
                return "Classificação Indicativa é de preenchimento obrigatório!";
            }

            if (tempoDuracao == 0) {
                return "Tempo Duração é de preenchimento obrigatório!";
            }

        } else {
            return "Deve ser um filme!";
        }

        return null;
    }
}
