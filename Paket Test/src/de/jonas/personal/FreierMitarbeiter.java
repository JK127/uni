package de.jonas.personal;

public class FreierMitarbeiter extends Mitarbeiter {
	Mitarbeiter ansprechpartner;
	
	public FreierMitarbeiter(String name, String einsatz, Mitarbeiter ap) {
		super(name, einsatz);
		this.ansprechpartner = ap;
	}
}
