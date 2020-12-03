package dominio.entidadesenum;

public enum TipoSala {
	
	NORMAL(1, "normal"),
	VIP(2, "VIP"),
	QUATRODIMENSOES(3, "4D");
	
	private Integer cod;
	private String descricao;
	
	private TipoSala(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoSala toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for(TipoSala ts : TipoSala.values()) {
			if (cod.equals(ts.getCod())) {
				return ts;
			}
		}
		
		throw new IllegalArgumentException("Id Inválido: " + cod);
	}

}
