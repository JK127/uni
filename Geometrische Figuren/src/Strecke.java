
public class Strecke {

	Punkt anfang;
	Punkt ende;
	
	public Strecke () {
		anfang = new Punkt();
		ende = new Punkt();
	}
	
	public Strecke (Punkt anfang, Punkt ende) {
		this.anfang = anfang;
		this.ende = ende;
	}
	
	public double länge() {
		return anfang.distance(ende);
	}
}
