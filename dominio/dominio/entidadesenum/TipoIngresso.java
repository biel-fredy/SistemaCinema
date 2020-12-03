package dominio.entidadesenum;

public enum TipoIngresso {
	
	INTEIRA(1, "inteira"),
	MEIA_ESTUDANTE(2, "estudante"),
	MEIA_VIVO(3, "meia-vivo"),
	MEIA_BRADESCO(4, "meia-bradesco");
	
	private Integer cod;
	private String descricao;
	
	private TipoIngresso(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoIngresso toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for(TipoIngresso ti : TipoIngresso.values()) {
			if (cod.equals(ti.getCod())) {
				return ti;
			}
		}
		
		throw new IllegalArgumentException("Id Inválido: " + cod);
	}

}
