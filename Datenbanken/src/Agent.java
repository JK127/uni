
public class Agent {

	private String name;
	private String city;
	private double prozent;

	private String[] names = { "Müller", "Kersting", "Brake", "Wältring", "Riemer", "Perez", "Wehmeier", "Heuing",
			"Leinweber", "Berkemeier" };
	private String[] cities = { "Greven", "Steinfurt", "Münster", "Dortmund", "München", "Berlin", "Hamburg",
			"Düsseldorf", "Köln", "Emsdetten" };

	public Agent() {
		setName(names[(int) (Math.random() * 10)]);
		setCity(cities[(int) (Math.random() * 10)]);
		setProzent(Math.random() * 5);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getProzent() {
		return prozent;
	}

	public void setProzent(double prozent) {
		this.prozent = prozent;
	}
}
