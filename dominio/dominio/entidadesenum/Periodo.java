package dominio.entidadesenum;

public enum Periodo {
	
	MANHA(1, "manhã"),
	TARDE(2, "tarde"),
	NOITE(3, "noite");
	
	private Integer cod;
	private String descricao;
	
	private Periodo(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static Periodo toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for(Periodo p : Periodo.values()) {
			if (cod.equals(p.getCod())) {
				return p;
			}
		}
		
		throw new IllegalArgumentException("Id Inválido: " + cod);
	}

}
