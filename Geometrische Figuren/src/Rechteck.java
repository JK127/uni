
public class Rechteck {
	
	Strecke a;
	Strecke b;
	static int anzahlRechtecke;
	
	public Rechteck(Strecke a, Strecke b) {
		this.a = a;
		this.b = b;
	}
	
	public double flaeche() {
		return a.länge() * b.länge();
	}
	
	public double umfang() {
		return (2 * a.länge() + 2 * b.länge());
	}
	
	public int getAnzahl() {
		return anzahlRechtecke;
	}
}
