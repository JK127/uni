
public class Bus {

	int busNummer;
	int leerGewicht;
	int zulGesGewicht;
	int kilowatt;
	int personenAnzahl;
	private boolean einsatzbereit;
	String fahrerName;
	String farbe;
	int wert;
	private static int anzahlBusse;
	private static int wertBusse;
	private static int anzahlDefBusse;
	private static int anzahlPers;
	
	public Bus (int busNr, int leerGew, int zulGesGew, int kw, int kapazität, String farb, int preis) {
		busNummer = busNr;
		leerGewicht = leerGew;
		zulGesGewicht = zulGesGew;
		kilowatt = kw;
		personenAnzahl = kapazität;
		einsatzbereit = false;
		
		anzahlBusse++;
		wertBusse += preis;
		anzahlDefBusse++;
		anzahlPers += kapazität;
	}
	
	public Bus (int busNr, int leerGew, int zulGesGew, int kw, int kapazität, String farb, int preis, String fahrer) {
		this(busNr, leerGew, zulGesGew, kw, kapazität, farb, preis);
		fahrerName = fahrer;
	}
	
	public int getAnzahlBusse() {
		return anzahlBusse;
	}
	
	public int getWertBusse() {
		return wertBusse;
	}
	
	public int getAnzahlDefBusse() {
		return anzahlDefBusse;
	}
	
	public int getAnzahlPers() {
		return anzahlPers;
	}
	
	public void setEinsatzbereit(boolean b) {
		
		if (einsatzbereit != b) {
			einsatzbereit = b;
			
			if (b) {
				anzahlDefBusse--;
			} else {
				anzahlDefBusse++;
			}
		}
	}
	
	public static void main(String[] args) {
		//new Bus(123, 20000, 30000, 200, 50);
	}

}
