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
					+ "	qty bigint NOT NULL,\n" + "	dollars decimal(10,2),\n" + "	PRIMARY KEY (oid)\n" + ");",
			// "DROP TRIGGER IF EXISTS dollarCalc;",
			// "delimiter //" + "CREATE TRIGGER dollarCalc BEFORE INSERT ON orders FOR EACH
			// ROW\n" + "BEGIN\n"
			// + " IF NEW.dollars = null\n" + " THEN\n"
			// + " SET NEW.dollars = ((SELECT p.price FROM products p WHERE
			// p.pid=NEW.pid)*NEW.qty);\n"
			// + " END IF;\n" + "END; //\n" + "delimiter ;"
	};

	// Pro Statement eine Connection: 348723
	// Nur eine Connection: 114953
	// Mit Batch: 15463
	public static void main(String[] args) {
		long start;
		long ende;

		CapDB db = new CapDB();
		start = System.currentTimeMillis();
		db.resetTables();
		db.fill(1000, 100000, 10000, 10000000);
		ende = System.currentTimeMillis();
		System.out.println("Dauer: " + ((ende - start) / 1000) / 60 + " Minuten.");
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

	private void fill(int agenten, int kunden, int produkte, int bestellungen) {

		try (Connection conn = DriverManager.getConnection(connStr, user, password);
				PreparedStatement ps = conn
						.prepareStatement("INSERT INTO agents (aname, city, percent) VALUES (?, ?, ?);")) {

			conn.setAutoCommit(false);
			Agent a;
			for (int i = 0; i < agenten; i++) {
				a = new Agent();
				ps.setString(1, a.getName());
				ps.setString(2, a.getCity());
				ps.setDouble(3, a.getProzent());
				ps.addBatch();

				if (i % 100000 == 0 || i == (agenten - 1)) {
					ps.executeBatch();
					ps.clearBatch();
				}
			}

			ps.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			System.err.println(e);
		}

		try (Connection conn = DriverManager.getConnection(connStr, user, password);
				PreparedStatement ps = conn
						.prepareStatement("INSERT INTO customers (cname, city, discnt) VALUES (?, ?, ?);")) {

			conn.setAutoCommit(false);
			Customer c;
			for (int i = 0; i < kunden; i++) {
				c = new Customer();
				ps.setString(1, c.getName());
				ps.setString(2, c.getCity());
				ps.setDouble(3, c.getDsc());
				ps.addBatch();

				if (i % 100000 == 0 || i == (kunden - 1)) {
					ps.executeBatch();
					ps.clearBatch();
				}
			}

			ps.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			System.err.println(e);
		}

		try (Connection conn = DriverManager.getConnection(connStr, user, password);
				PreparedStatement ps = conn
						.prepareStatement("INSERT INTO products (pname, city, quantity, price) VALUES (?, ?, ?, ?);")) {

			conn.setAutoCommit(false);
			Product p;
			for (int i = 0; i < produkte; i++) {
				p = new Product();
				ps.setString(1, p.getName());
				ps.setString(2, p.getCity());
				ps.setDouble(3, p.getQuantity());
				ps.setDouble(4, p.getPrice());
				ps.addBatch();

				if (i % 100000 == 0 || i == (produkte - 1)) {
					ps.executeBatch();
					ps.clearBatch();
				}
			}
			ps.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			System.err.println(e);
		}

		try (Connection conn = DriverManager.getConnection(connStr, user, password);
				PreparedStatement ps = conn
						.prepareStatement("INSERT INTO orders (month, cid, pid, aid, qty) VALUES (?, ?, ?, ?, ?);")) {

			conn.setAutoCommit(false);
			Order o;
			for (int i = 0; i < bestellungen; i++) {
				o = new Order(kunden, produkte, agenten);
				ps.setString(1, o.getMonth());
				ps.setInt(2, o.getCid());
				ps.setInt(3, o.getPid());
				ps.setInt(4, o.getAid());
				ps.setInt(5, o.getQty());
				ps.addBatch();

				if (i % 100000 == 0 || i == (bestellungen - 1)) {
					ps.executeBatch();
					ps.clearBatch();
				}
			}

			ps.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			System.err.println(e);
		}

	}
}
