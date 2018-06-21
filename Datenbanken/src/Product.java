
public class Product {

	private String name;
	private String city;
	private int quantity;
	private double price;

	private String[] names = { "Stift", "Rasierer", "Bürste", "Pinsel", "Kaffee", "Brot", "Uhr", "Kreide", "Tasse",
			"Telefon" };
	private String[] cities = { "Greven", "Steinfurt", "Münster", "Dortmund", "München", "Berlin", "Hamburg",
			"Düsseldorf", "Köln", "Emsdetten" };

	public Product() {
		setName(names[(int) (Math.random() * 10)]);
		setCity(cities[(int) (Math.random() * 10)]);
		setQuantity((int) (Math.random() * 1000));
		setPrice(Math.random() * 8.5);
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
