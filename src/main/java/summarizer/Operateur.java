package summarizer;

public enum Operateur {
	//Les valeurs énumérées
	SUP("upper", ">"), 
	INF("lower", "<"), 
	EQUAL("equal", "=="),
	SUP_EQUAL("upper or equal", ">="),
	INF_EQUAL("lower or equal", "<="),
	DIFFERENT("different", "!=");
	
	private String symbol = "";
	private String name = "";
	
	//Constructeur
	Operateur(String name, String symbol) {
		this.symbol = symbol;
		this.name = name;
		
	}
	
	/**
	 * 
	 * @return Symbole
	 */
	public String getSymbol(){
		return this.symbol;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

}
