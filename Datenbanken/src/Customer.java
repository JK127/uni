
public class Customer {
	private String name;
	private String city;
	private double dsc;

	private String[] names = { "Florian", "Karsten", "Kustomer", "Willy", "Rudi", "Daniel", "Marvin", "Justin", "Kevin",
			"Falk" };
	private String[] cities = { "Greven", "Steinfurt", "Münster", "Dortmund", "München", "Berlin", "Hamburg",
			"Düsseldorf", "Köln", "Emsdetten" };

	public Customer() {
		setName(names[(int) (Math.random() * 10)]);
		setCity(cities[(int) (Math.random() * 10)]);
		setDsc(Math.random() * 5);
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

	public double getDsc() {
		return dsc;
	}

	public void setDsc(double dsc) {
		this.dsc = dsc;
	}

}
