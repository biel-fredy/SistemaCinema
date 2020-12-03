package core.dao.impl;

public class ResultadoDAO {
	
	private String msg = null;
	private Integer idGerado = null;
	
	public ResultadoDAO(String msg, Integer idGerado){
		this.msg = msg;
		this.idGerado = idGerado;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getIdGerado() {
		return idGerado;
	}
	public void setIdGerado(Integer idGerado) {
		this.idGerado = idGerado;
	}
	
	

}
