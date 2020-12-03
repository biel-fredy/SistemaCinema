package dominio.entidades;

import java.util.Date;

import core.dao.anotacoes.Tabela;
import dominio.EntidadeDominio;

@Tabela(nomeTabela = "CLIENTES", trigama = "cli")
public class Cliente extends EntidadeDominio {
	
	private String pNome;
	private String mNome;
	private String uNome;
	private String cpf;
	private Date dataNascimento;
	
	public Cliente() {
		super();
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getpNome() {
		return pNome;
	}
	public void setpNome(String pNome) {
		this.pNome = pNome;
	}
	public String getmNome() {
		return mNome;
	}
	public void setmNome(String mNome) {
		this.mNome = mNome;
	}
	public String getuNome() {
		return uNome;
	}
	public void setuNome(String uNome) {
		this.uNome = uNome;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	
}
