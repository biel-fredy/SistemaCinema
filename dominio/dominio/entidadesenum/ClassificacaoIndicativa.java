package dominio.entidadesenum;

public enum ClassificacaoIndicativa {
	
	LIVRE(1, "Classifica��o: Livre."),
	DEZ_ANOS(2, "Classifica��o: 10 anos."),
	DOZE_ANOS(3, "Classifica��o: 12 anos."),
	QUATORZE_ANOS(4, "Classifica��o: 14 anos."),
	DEZESSEIS_ANOS(5, "Classifica��o: 16 anos."),
	DEZOITO_ANOS(6, "Classifica��o: 18 anos.");
	
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
		
		throw new IllegalArgumentException("Id Inv�lido: " + cod);
	}

}
