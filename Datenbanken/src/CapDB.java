import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CapDB {
	private String user = "root";
	private String password = "Pa$$w0rd";
	private String connStr = "jdbc:mysql://localhost:3306/CAP_DB?verifyServerCertificate=false&useSSL=true";
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

	// Pro Statement eine Connection: 348723
	// Nur eine Connection: 114953
	// Mit Batch: 15463
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

		try (Connection conn = DriverManager.getConnection(connStr, user, password);
				PreparedStatement ps = conn
						.prepareStatement("INSERT INTO agents (aname, city, percent) VALUES (?, ?, ?);")) {

			conn.setAutoCommit(false);

			for (Agent a : ag) {
				ps.clearParameters();
				ps.setString(1, a.getName());
				ps.setString(2, a.getCity());
				ps.setDouble(3, a.getProzent());
				ps.addBatch();
			}

			ps.executeLargeBatch();

		} catch (SQLException e) {
			System.err.println(e);
		}

		try (Connection conn = DriverManager.getConnection(connStr, user, password);
				PreparedStatement ps = conn
						.prepareStatement("INSERT INTO customers (cname, city, discnt) VALUES (?, ?, ?);")) {

			conn.setAutoCommit(false);

			for (Customer c : cm) {
				ps.clearParameters();
				ps.setString(1, c.getName());
				ps.setString(2, c.getCity());
				ps.setDouble(3, c.getDsc());
				ps.addBatch();
			}

			ps.executeLargeBatch();

		} catch (SQLException e) {
			System.err.println(e);
		}

		try (Connection conn = DriverManager.getConnection(connStr, user, password);
				PreparedStatement ps = conn
						.prepareStatement("INSERT INTO products (pname, city, quantity, price) VALUES (?, ?, ?, ?);")) {

			conn.setAutoCommit(false);

			for (Product p : pr) {
				ps.clearParameters();
				ps.setString(1, p.getName());
				ps.setString(2, p.getCity());
				ps.setDouble(3, p.getQuantity());
				ps.setDouble(4, p.getPrice());
				ps.addBatch();
			}

			ps.executeLargeBatch();

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
