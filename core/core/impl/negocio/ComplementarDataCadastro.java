package core.impl.negocio;

import core.IStrategy;
import dominio.EntidadeDominio;

import java.util.Date;

public class ComplementarDataCadastro implements IStrategy {
    @Override
    public String processar(EntidadeDominio entidade) {
        if(entidade !=null){
            Date data = new Date();
            entidade.setDataCriacao(data);
        }else{
            return "Entidade nula!";
        }
        return null;
    }
}
