
public class Order {
	private String month;
	private int cid;
	private int pid;
	private int aid;
	private int qty;
	private double dollars;

	String[] mon = { "jan", "feb", "mar", "apr", "mai", "jun", "jul", "aug", "sep", "okt", "nov", "dez" };

	/**
	 * 
	 * @param customer
	 *            Anzahl erstellter Kunden
	 * @param product
	 *            Anzahl erstellter Produkte
	 * @param agent
	 *            Anzahl erstellter Agenten
	 */
	public Order(int customer, int product, int agent) {
		setMonth(mon[(((int) Math.random()) * 12) + 1]);
		setCid((int) (Math.random() * customer + 1));
		setPid((int) (Math.random() * product + 1));
		setAid((int) (Math.random() * agent + 1));
		setQty((int) (Math.random() * 15));
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getDollars() {
		return dollars;
	}

	public void setDollars(double dollars) {
		this.dollars = dollars;
	}

}
