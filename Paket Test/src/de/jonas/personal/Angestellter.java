package de.jonas.personal;

public class Angestellter extends Mitarbeiter{
	private int personalnummer;
	private float gehalt;
	
	public Angestellter(String name, String einsatz, int pNr, float gh) {
		super(name, einsatz);
		this.personalnummer = pNr;
		this.gehalt = gh;
	}
	
	public float getGehalt() {
		return gehalt;
	}
	public void setGehalt(float gehalt) {
		this.gehalt = gehalt;
	}
}
