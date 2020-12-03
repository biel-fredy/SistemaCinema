package dominio.entidades;

import dominio.EntidadeDominio;

public class Usuario extends EntidadeDominio {
	
	private String login;
	private String senha;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	@Override
	public String toString() {
		return "Usuario [login=" + login + ", senha=" + senha + "]";
	}
}
