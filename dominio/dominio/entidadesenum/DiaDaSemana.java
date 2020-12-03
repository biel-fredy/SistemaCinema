package dominio.entidadesenum;

public enum DiaDaSemana {
	
	DOMINGO (1, "domingo"),
	SEGUNDA_FEIRA(2, "segunda-feira"),
	TERCA_FEIRA(3, "terça-feira"),
	QUARTA_FEIRA(4, "quarta-feira"),
	QUINTA_FEIRA(5, "quinta-feira"),
	SEXTA_FEIRA(6, "sexta-feira"),
	SABADO(7, "sábado");
	
	private Integer cod;
	private String descricao;
	
	private DiaDaSemana(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static DiaDaSemana toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for(DiaDaSemana ds : DiaDaSemana.values()) {
			if (cod.equals(ds.getCod())) {
				return ds;
			}
		}
		
		throw new IllegalArgumentException("Id Inválido: " + cod);
	}

}
