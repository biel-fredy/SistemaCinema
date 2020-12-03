package web.command.impl;

import core.IFachada;
import core.impl.controle.Fachada;
import web.command.ICommand;

public abstract class AbstractCommand implements ICommand {
	
	protected IFachada fachada = new Fachada();

}
