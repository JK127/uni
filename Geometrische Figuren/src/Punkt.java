
public class Punkt {
	double x;
	double y;

	public Punkt() {
		x = 0;
		y = 0;
	}

	public Punkt(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public boolean equals(Punkt vergleichspunkt) {
		if (this.x == vergleichspunkt.x && this.y == vergleichspunkt.y) {
			return true;
		} else {
			return false;
		}
	}

	public double distance(Punkt vergleichspunkt) {
		return Math.sqrt(Math.pow(vergleichspunkt.x - this.x, 2) + Math.pow(vergleichspunkt.y - this.y, 2));

	}

	public static void main(String[] args) {
		Punkt p1 = new Punkt(1, 1);
		Punkt p2 = new Punkt(1, 1);
		Punkt p3 = new Punkt(1, 0);
		Punkt p4 = new Punkt(4, 5);
		Punkt p5 = new Punkt(7, 8);
		
		//sollte true sein
		if (p1.equals(p2)) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
				
		
		//sollte false sein
		if (p1.equals(p3)) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
		
		//Distance
		System.out.println(p1.distance(p3));
		
		Strecke s1 = new Strecke(p1, p3);
		Strecke s2 = new Strecke(p4, p5);
		System.out.println(s1.länge());
		
		Rechteck r1 = new Rechteck(s1, s2);
		System.out.println(r1.flaeche());
		
		Quadrat q1 = new Quadrat(s2);
		System.out.println(q1.flaeche());
	}
}