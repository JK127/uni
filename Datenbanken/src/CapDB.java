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
					+ "	qty smallint NOT NULL,\n" + "	dollars decimal(10,2) NOT NULL,\n" + "	PRIMARY KEY (oid)\n"
					+ ");" };

	public static void main(String[] args) {
		CapDB db = new CapDB();
		db.resetTables();
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
}
