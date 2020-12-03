package dominio.entidadesenum;

public enum ClassificacaoIndicativa {
	
	LIVRE(1, "Classificação: Livre."),
	DEZ_ANOS(2, "Classificação: 10 anos."),
	DOZE_ANOS(3, "Classificação: 12 anos."),
	QUATORZE_ANOS(4, "Classificação: 14 anos."),
	DEZESSEIS_ANOS(5, "Classificação: 16 anos."),
	DEZOITO_ANOS(6, "Classificação: 18 anos.");
	
	private Integer cod;
	private String descricao;
	
	private ClassificacaoIndicativa(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static ClassificacaoIndicativa toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for(ClassificacaoIndicativa ci : ClassificacaoIndicativa.values()) {
			if (cod.equals(ci.getCod())) {
				return ci;
			}
		}
		
		throw new IllegalArgumentException("Id Inválido: " + cod);
	}

}
