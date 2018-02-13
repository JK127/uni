
public class Quadrat extends Rechteck {

	static int anzahlQuadrate;
	
	public Quadrat(Strecke a) {
		super(a, a);
	}
	
	public int getAnzahl() {
		return anzahlQuadrate;
	}
}
