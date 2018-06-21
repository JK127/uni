import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CapDB {
	private String user = "root";
	private String password = "Pa$$w0rd";
	private String connStr = "jdbc:mysql://localhost:3306/CAP_DB";
	private String[] tables = { "customers", "products", "agents", "orders" };
	private String[] createTables = {
			"CREATE TABLE customers(\n" + "	cid int NOT NULL AUTO_INCREMENT,\n" + "	cname varchar(15) NOT NULL,\n"
					+ "	city varchar(15) NOT NULL,\n" + "	discnt decimal(3,2) NOT NULL,\n" + "	PRIMARY KEY (cid)\n"
					+ ");",
			"CREATE TABLE products(\n" + "	pid int NOT NULL AUTO_INCREMENT,\n" + "	pname varchar(15) NOT NULL,\n"
					+ "	city varchar(15) NOT NULL,\n" + "	quantity smallint NOT NULL,\n"
					+ "	price decimal(10,2) NOT NULL,\n" + "	PRIMARY KEY (pid)\n" + ");",
			"CREATE TABLE agents(\n" + "	aid int NOT NULL AUTO_INCREMENT,\n" + "	aname varchar(15) NOT NULL,\n"
					+ "	city varchar(15) NOT NULL,\n" + "	percent smallint NOT NULL,\n" + "	PRIMARY KEY (aid)\n"
					+ ");",
			"CREATE TABLE orders(\n" + "	oid int NOT NULL AUTO_INCREMENT,\n" + "	month varchar(10) NOT NULL,\n"
					+ "	cid int NOT NULL REFERENCES customers(cid),\n"
					+ "	pid int NOT NULL REFERENCES products(pid),\n" + "	aid int NOT NULL REFERENCES agents(aid),\n"
					+ "	qty bigint NOT NULL,\n" + "	dollars decimal(10,2) NOT NULL,\n" + "	PRIMARY KEY (oid)\n"
					+ ");" };

	// ALT: 348723
	// NEU: 114953
	public static void main(String[] args) {
		long start;
		long ende;

		CapDB db = new CapDB();
		start = System.currentTimeMillis();
		db.resetTables();
		db.fill(1000, 100000, 10000);
		ende = System.currentTimeMillis();
		System.out.println("Dauer: " + (ende - start));
	}

	private void resetTables() {

		for (String tableName : tables) {
			String reset = String.format("DROP TABLE IF EXISTS %s;", tableName);

			try (Connection conn = DriverManager.getConnection(connStr, user, password);
					PreparedStatement ps = conn.prepareStatement(reset)) {
				ps.execute();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}

		for (String create : createTables) {
			try (Connection conn = DriverManager.getConnection(connStr, user, password);
					PreparedStatement ps = conn.prepareStatement(create)) {
				ps.execute();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

	private void fill(int agenten, int kunden, int produkte) {
		Agent[] ag = getAgenten(agenten);
		Customer[] cm = getCustomers(kunden);
		Product[] pr = getProducts(produkte);
		try (Connection conn = DriverManager.getConnection(connStr, user, password)) {

			for (Agent a : ag) {
				String insert = String.format("INSERT INTO agents (aname, city, percent) VALUES ('%s', '%s', %s);",
						a.getName(), a.getCity(), a.getProzent());
				try (PreparedStatement ps = conn.prepareStatement(insert)) {
					ps.execute();
				} catch (SQLException e) {
					System.err.println(e);
				}
			}

			for (Customer c : cm) {
				String insert = String.format("INSERT INTO customers (cname, city, discnt) VALUES ('%s', '%s', %s);",
						c.getName(), c.getCity(), c.getDsc());
				try (PreparedStatement ps = conn.prepareStatement(insert)) {
					ps.execute();
				} catch (SQLException e) {
					System.err.println(e);
				}
			}

			for (Product p : pr) {
				String insert = String.format(
						"INSERT INTO products (pname, city, quantity, price) VALUES ('%s', '%s', %s, %s);", p.getName(),
						p.getCity(), p.getQuantity(), p.getPrice());
				try (PreparedStatement ps = conn.prepareStatement(insert)) {
					ps.execute();
				} catch (SQLException e) {
					System.err.println(e);
				}
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	private Agent[] getAgenten(int anz) {
		Agent[] agenten = new Agent[anz];
		for (int i = 0; i < anz; i++) {
			agenten[i] = new Agent();
		}
		return agenten;
	}

	private Customer[] getCustomers(int anz) {
		Customer[] customers = new Customer[anz];
		for (int i = 0; i < anz; i++) {
			customers[i] = new Customer();
		}
		return customers;
	}

	private Product[] getProducts(int anz) {
		Product[] products = new Product[anz];
		for (int i = 0; i < anz; i++) {
			products[i] = new Product();
		}
		return products;
	}
}
