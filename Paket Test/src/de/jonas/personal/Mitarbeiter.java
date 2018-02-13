package de.jonas.personal;

public class Mitarbeiter {
 
	private String name;
	private String einsatzbereich;
	
	public String getEinsatzbereich() {
		return einsatzbereich;
	}
	
	public void setEinsatzbereich(String einsatzbereich) {
		this.einsatzbereich = einsatzbereich;
	}
	
	public Mitarbeiter(String name, String einsatz) {
		this.name = name;
		this.einsatzbereich = einsatz;
	}
}
